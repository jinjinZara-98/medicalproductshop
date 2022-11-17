package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.entity.Item;
import capstonedesign.medicalproduct.domain.ItemSearch;
import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.dto.ItemDto;
import capstonedesign.medicalproduct.dto.QItemDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<ItemDto> nameSearch(ItemSearch itemSearch, Pageable pageable) {

        QItem i = QItem.item;

        String itemName = itemSearch.getItemName();

        BooleanBuilder builder = new BooleanBuilder();

        if(hasText(itemSearch.getItemName())) {
            builder.and(i.name.contains(itemName));
        }

        QueryResults<ItemDto> findItem = queryFactory
                .select(new QItemDto(i.id, i.name, i.imageSrc, i.price, i.rate))
                .from(i)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<ItemDto> content = findItem.getResults();

        long total = findItem.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    //카테고리 이름과 일치하는 상품을 갖고오는
    public Page<ItemDto> categorySearch(String category, Pageable pageable) {

        QItem i = QItem.item;

        QueryResults<ItemDto> findItem = queryFactory
                .select(new QItemDto(i.id, i.name, i.imageSrc, i.price, i.rate))
                .from(i)
                .where(i.category.eq(category))
                //파라미터로 들어온 pageble에서 어디서 부터 시작인지 크기는 얼마인지 뽑아오는
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                //fetch()는 반환타입이 데이터 컨텐츠를 List로 바로 가져오게됨, 내용만 가져오는
                //fetchResults()를 쓰면 컨텐츠용쿼리와 카운트용쿼리 알아서 2번 날릴 수 있는
                //조인이런게 다 붙어 최적화를 못함
                //토탈쿼리는 orderby를 지운다?
                .fetchResults();

        //getResults()로 실제 데이터
        List<ItemDto> content = findItem.getResults();

        //getTotal()는 토탈카운트쿼리 날려 총 쿼리 2번
        long total = findItem.getTotal();

        //PageImpl이 page의 구현체
        //new PageImpl<>로 반환타입 page
        return new PageImpl<>(content, pageable, total);
    }
}
