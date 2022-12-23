package capstonedesign.medicalproduct.controller.restapi;

import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.repository.ItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class ItemAPIController {

    private final ItemRepository itemRepository;

    /**
     * 모든 상품 조회
     */
    @GetMapping("/api/items")
    public List<ItemAPIDto> findItems(@PageableDefault(size = 8) Pageable pageable) {

        Page<Item> items = itemRepository.findAll(pageable);

        List<ItemAPIDto> result = items.stream()
                //new SimpleOrderDto(o)) 생성하면서 get메서드 쓸때 지연로딩 초기화
                //그 객체를 통해 메서드 사용해 직접 사용하므로
                .map(item -> new ItemAPIDto(item))
                .collect(toList());

        return result;
    }

    /**
     * 특정 상품 조회
     */
    @GetMapping("/api/items/{itemId}")
    public ItemAPIDto findItem(@PathVariable("itemId") long itemId) {
        Item item = itemRepository.getById(itemId);
        ItemAPIDto result = new ItemAPIDto(item);

        return result;
    }

    @Data
    public static class ItemAPIDto {

        private long id;
        private String name;
        private String imageSrc;
        private String category;
        private int price;
        private String introduction;
        private int rate;

        public ItemAPIDto(Item item) {
            this.id = item.getId();
            this.name = item.getName();
            this.imageSrc = item.getImageSrc();
            this.category = item.getCategory();
            this.price = item.getPrice();
            this.introduction = item.getIntroduction();
            this.rate = item.getRate();
        }
    }
}
