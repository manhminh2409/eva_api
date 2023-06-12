package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.config.Constants;
import com.evadeeva.evadeeva.mapper.OrderItemMapper;
import com.evadeeva.evadeeva.mapper.OrdersMapper;
import com.evadeeva.evadeeva.mapper.ProductMapper;
import com.evadeeva.evadeeva.models.*;
import com.evadeeva.evadeeva.repositories.*;
import com.evadeeva.evadeeva.services.OrdersService;
import com.evadeeva.evadeeva.web.dto.request.OrderItemRequest;
import com.evadeeva.evadeeva.web.dto.response.OrdersResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final SizeRepository sizeRepository;
    private final OrdersMapper ordersMapper;
    private final OrderItemMapper orderItemMapper;
    private final ProductMapper productMapper;
    public OrdersServiceImpl(OrdersRepository ordersRepository,
                             OrderItemRepository orderItemRepository,
                             UserRepository userRepository,
                             SizeRepository sizeRepository, OrdersMapper ordersMapper,
                             OrderItemMapper orderItemMapper, ProductMapper productMapper) {
        this.ordersRepository = ordersRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.sizeRepository = sizeRepository;
        this.ordersMapper = ordersMapper;
        this.orderItemMapper = orderItemMapper;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public OrdersResponse getOrderByType(long userId, int type) {
        if (type != 0 && type != 1){
            type = 0;
        }
        User user = userRepository.findById(userId).orElseThrow();
        Orders orders = ordersRepository.findByUserAndType(user, type);
        return ordersMapper.mapToResponse(orders);
    }

    @Override
    @Transactional
    public OrdersResponse addItemToCart(long userId, OrderItemRequest orderItemRequest) {
        User user = userRepository.findById(userId).orElseThrow();
        Orders checkOrders = ordersRepository.findByUserAndType(user, Constants.CART_TYPE);
        Size size = sizeRepository.findById(orderItemRequest.getSizeId()).orElseThrow();
        if (orderItemRequest.getQuantity() <= 0 || orderItemRequest.getQuantity() > size.getTotal()){
            return null;
        }else {
            Color color = size.getColor();
            Product product = color.getProduct();
            if (product.getSalePrice() != 0) {
                orderItemRequest.setSellPrice(product.getSalePrice());
            } else {
                orderItemRequest.setSellPrice(product.getPrice());
            }

            OrderItem orderItem = orderItemMapper.mapToModel(orderItemRequest);
            if (checkOrders == null) {
                //Chưa có giỏ hàng
                //Tạo giỏ hàng mới và lưu thông tin
                Orders orders = new Orders();
                orders.setUser(user);
                orders.setType(0);
                ordersRepository.save(orders);
                orderItem.setOrders(orders);
                orderItemRepository.save(orderItem);
                return ordersMapper.mapToResponse(orders);
            } else {
                OrderItem checkOrderItem = orderItemRepository.findBySizeAndAndOrders(size, checkOrders);
                if (checkOrderItem == null) {
                    //Có giỏ hàng nhưng chưa có sản phẩm đó
                    orderItem.setOrders(checkOrders);
                    orderItemRepository.save(orderItem);
                } else {
                    //Có giỏ hàng và đã tồn tại sản phẩm đó
                    checkOrderItem.setQuantity(orderItemRequest.getQuantity() + checkOrderItem.getQuantity());
                    if (checkOrderItem.getSellPrice() != orderItemRequest.getSellPrice()) {
                        checkOrderItem.setSellPrice(orderItemRequest.getSellPrice());
                    }
                    orderItemRepository.save(checkOrderItem);
                }
                return ordersMapper.mapToResponse(checkOrders);
            }
        }
    }
}
