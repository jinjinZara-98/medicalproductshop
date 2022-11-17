package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.CartSearch;
import capstonedesign.medicalproduct.domain.entity.QCart;
import capstonedesign.medicalproduct.domain.entity.QItem;
import capstonedesign.medicalproduct.domain.entity.QMember;
import capstonedesign.medicalproduct.dto.CartDto;
import capstonedesign.medicalproduct.dto.QCartDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class CartQueryRepository {

    private final JPAQueryFactory queryFactory;

    //로그인한 회원의 장바구니 아이템 가져오는
    public List<CartDto> cartItems(CartSearch cartSearch, Long memberId){

        QMember member = QMember.member;
        QItem item = QItem.item;
        QCart cart = QCart.cart;

        BooleanBuilder builder =
                new BooleanBuilder(cart.member.id.eq(memberId).and(cart.item.id.eq(item.id) ) );

        if (hasText(cartSearch.getItemName())) {
            builder.and(item.name.contains(cartSearch.getItemName()));
        }

        List<CartDto> result = queryFactory
                .select(new QCartDto(cart.id, cart.item.id, cart.item.name, item.imageSrc, item.price, cart.quantity, cart.status))
                .from(cart)
                .join(cart.member, member)
                .join(cart.item, item)
                .where(builder)
                .fetch();

        return result;
    }

}
