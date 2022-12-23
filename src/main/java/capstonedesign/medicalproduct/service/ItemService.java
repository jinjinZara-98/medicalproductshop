package capstonedesign.medicalproduct.service;
import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.ItemSearch;
import capstonedesign.medicalproduct.dto.mvc.ItemDto;
import capstonedesign.medicalproduct.repository.ItemRepository;
import capstonedesign.medicalproduct.repository.ItemQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemQueryRepository itemQueryRepository;

    //상품 넣기
    @Transactional
    public void save(String category, String imageSrc, String introduction,String name, int price, int rate){
       Item item = Item.createItem(name, imageSrc, category, price, introduction, rate);
       itemRepository.save(item);
    }

    public Page<ItemDto> findAllByName(ItemSearch itemSearch, int page) {

        return itemQueryRepository.findAllByName(itemSearch, PageRequest.of(page,8, Sort.Direction.ASC, "id"));
    }

    //카테고리 이름과 일치하는 상품을 가져오는
    public Page<ItemDto> findAllByCategory(String category, int page) {

        return itemQueryRepository.findAllByCategory(category, PageRequest.of(page,8, Sort.Direction.ASC, "id"));
    }

    //상품을 클릭해 상품상세화면으로 넘어갈때 사용하는, 파라미터로 들어온 id에 맞는 상품 가져오기
    public Item findById(long itemId) {

        return itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품은 없습니다. id" + itemId));
    }
}
