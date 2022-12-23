package capstonedesign.medicalproduct.service;

import capstonedesign.medicalproduct.domain.ItemSearch;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.dto.mvc.ItemDto;
import capstonedesign.medicalproduct.repository.ItemQueryRepository;
import capstonedesign.medicalproduct.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemQueryRepository itemQueryRepository;

    @Test
    @DisplayName("총 판매 상품 개수 확인")
    public void itemListSize() throws Exception {

        List<Item> items = itemRepository.findAll();
        assertThat(items.size()).isEqualTo(22);
    }

    @Test
    @DisplayName("상품 검색 기능 확인")
    public void searchItem() throws Exception {

        ItemSearch itemSearch = new ItemSearch();
        //검색어 설정
        itemSearch.setItemName("가위");

        //검색할 때 0 페이지로?
        Page<ItemDto> items =
                itemQueryRepository.findAllByName(itemSearch, PageRequest.of(0,8, Sort.Direction.ASC, "id"));

        //가위 검색했을 때 총 1개의 페이지, 총 2개의 상품
        assertThat(items.getTotalPages()).isEqualTo(1);
        assertThat(items.getContent().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카테고리에 속한 상품 확인")
    public void categoryItems() throws Exception {

        Page<ItemDto> items =
                itemQueryRepository.findAllByCategory("의료기기", PageRequest.of(0,8, Sort.Direction.ASC, "id"));

        assertThat(items.getTotalPages()).isEqualTo(2);
        assertThat(items.getContent().size()).isEqualTo(8);
    }
}