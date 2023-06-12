package com.evadeeva.evadeeva.services.impl;

import com.evadeeva.evadeeva.mapper.ColorMapper;
import com.evadeeva.evadeeva.mapper.ProductImageMapper;
import com.evadeeva.evadeeva.mapper.ProductMapper;
import com.evadeeva.evadeeva.mapper.SizeMapper;
import com.evadeeva.evadeeva.models.*;
import com.evadeeva.evadeeva.repositories.*;
import com.evadeeva.evadeeva.services.ProductService;
import com.evadeeva.evadeeva.utils.Utils;
import com.evadeeva.evadeeva.web.dto.request.ColorRequest;
import com.evadeeva.evadeeva.web.dto.request.ProductImageRequest;
import com.evadeeva.evadeeva.web.dto.request.ProductRequest;
import com.evadeeva.evadeeva.web.dto.request.SizeRequest;
import com.evadeeva.evadeeva.web.dto.response.ProductResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import static com.evadeeva.evadeeva.config.Constants.ACTIVE_STATUS;
import static com.evadeeva.evadeeva.config.Constants.PRODUCT_TYPE;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SizeMapper sizeMapper;
    private final ColorMapper colorMapper;
    private final ProductImageMapper productImageMapper;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final SizeRepository sizeRepository;
    private final ProductImageRepository productImageRepository;
    private final Utils utils;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProductMapper productMapper,
                              SizeMapper sizeMapper,
                              ColorMapper colorMapper,
                              ProductImageMapper productImageMapper,
                              CategoryRepository categoryRepository,
                              ColorRepository colorRepository,
                              SizeRepository sizeRepository,
                              ProductImageRepository productImageRepository,
                              Utils utils) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.sizeMapper = sizeMapper;
        this.colorMapper = colorMapper;
        this.productImageMapper = productImageMapper;
        this.categoryRepository = categoryRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.productImageRepository = productImageRepository;
        this.utils = utils;
    }

    @Override
    public List<ProductResponse> getProducts(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Product> products = productRepository.findAllByStatus(pageable, ACTIVE_STATUS);
        if (products.isEmpty()){
            return null;
        }else {
            return products.getContent().stream()
                    .map(productMapper::mapModelToResponse)
                    .toList();
        }
    }

    @Override
    public ProductResponse getProduct(long productId) {
        Product product =productRepository.findByIdAndStatus(productId, ACTIVE_STATUS);
        if (product != null){
            return productMapper.mapModelToResponse(product);
        }else {
            return null;
        }
    }

    @Override
    public ProductResponse getProductBySize(long sizeId) {
        Size size = sizeRepository.findById(sizeId).orElseThrow();
        Color color = size.getColor();
        Product product = color.getProduct();
        return productMapper.mapModelToResponse(product);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(long categoryId, int pageNo, int pageSize, String sortBy) {
        Category category = categoryRepository.findByStatusAndIdAndType(ACTIVE_STATUS, categoryId, PRODUCT_TYPE);
        if (category != null) {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
            Page<Product> products = productRepository.findAllByProductCategoryAndStatus(pageable, category, ACTIVE_STATUS);

            if (products.isEmpty()) {
                return null;
            }else {
                return products.getContent().stream()
                        .map(productMapper::mapModelToResponse)
                        .toList();
            }
        }else {
            return null;
        }
    }

    private void saveColorsAndSizes(List<ColorRequest> colorRequests, long productId) {
        if (colorRequests != null) {
            for (ColorRequest colorRequest : colorRequests) {
                colorRequest.setProductId(productId);
                Color color = colorMapper.mapRequestedToModel(colorRequest);
                color.setSizes(null);
                Color newColor = colorRepository.save(color);
                List<SizeRequest> sizeRequests = colorRequest.getSizes().stream().toList();
                for (SizeRequest sizeRequest : sizeRequests) {
                    Size size = sizeMapper.mapRequestedToModel(sizeRequest);
                    size.setSold(0);
                    size.setColor(newColor);
                    if (size.getColor() != null) {
                        sizeRepository.save(size);
                    }
                }

            }
        }

    }

//    private void saveImages(MultipartFile[] images, long productId) throws IOException {
//        // Lặp qua từng file trong danh sách images.
//        for (MultipartFile file : images) {
//            // Lấy tên file và extension.
//            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//            String extension = FilenameUtils.getExtension(filename);
//
//            // Tạo đường dẫn tới file ảnh.
//            String imagePath = "/assets/images/uploads/products/" + filename;
//
//            // Tạo file mới với đường dẫn được chỉ định.
//
//            File savedFile = new File("D:/DATN2023/src/main/resources/static/assets/images/uploads/products/"+ filename);
//
//            // Lưu file vào đường dẫn.
//            try (OutputStream outputStream = new FileOutputStream(savedFile)) {
//                outputStream.write(file.getBytes());
//            }
//            // Tạo một đối tượng Image mới và thiết lập thuộc tính url.
//            ProductImage image = new ProductImage();
//            image.setUrl(imagePath);
//            image.setProduct(productRepository.findById(productId).orElseThrow());
//
//            // Lưu đối tượng Image vào cơ sở dữ liệu.
//            productImageRepositoty.save(image);
//        }
//    }

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest productRequest)  {
        long lastId = -1;
        try{
            lastId = productRepository.findNewestId();
        }catch (Exception ignored){
        }
        String sku = utils.generateRandomCharacters(2) + String.valueOf(productRequest.getUserId()) +
                utils.generateRandomCharacters(3) + String.valueOf( lastId + 1);

        Product product = productMapper.mapRequestedToModel(productRequest);
        product.setColors(null);
        product.setSku(sku);
        // set current date
        Date currentDate = new Date();
        product.setCreatedDate(currentDate);
        product.setModifiedDate(currentDate);

        product.setStatus(1);

        product.setProductImages(null);

        // Save new product to database
        Product newProduct = productRepository.save(product);

        // Get id of product create recently
        long productId = newProduct.getId();

        List<ColorRequest> colorRequests = productRequest.getColors();
        this.saveColorsAndSizes(colorRequests, productId);

        for (ProductImageRequest pImg : productRequest.getImages()) {
            ProductImage image = new ProductImage();
            image.setUrl(pImg.getUrl());
            image.setProduct(productRepository.findById(productId).orElseThrow());
            productImageRepository.save(image);
        }
        // Save image into table image
//        this.saveImages(productRequest.getImages(), productId);


        Product product1 = productRepository.findById(productId).orElseThrow();
        List<ProductImage> productImages = productImageRepository.findByProduct(product1);
        product1.setProductImages(productImages);
        return productMapper.mapModelToResponse(product1);
    }
}
