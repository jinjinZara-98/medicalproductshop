package capstonedesign.medicalproduct.repository;

import capstonedesign.medicalproduct.domain.*;
import capstonedesign.medicalproduct.domain.entity.*;
import capstonedesign.medicalproduct.dto.mvc.ordered.OrderedItemDto;
import capstonedesign.medicalproduct.dto.ordered.QOrderedItemDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.querydsl.jpa.JPAExpressions.*;
import static org.springframework.util.StringUtils.*;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<OrderedItemDto> findAllOrderItemByMemberIdAndOrderInfo(long memberId, OrderSearch orderSearch) {

        QMember m = new QMember("m");
        QItem i = new QItem("i");
        QOrder o = new QOrder("o");
        QOrderItem oi = new QOrderItem("oi");
        QReview r = new QReview("r");

        BooleanBuilder builder = new BooleanBuilder(o.member.id.eq(memberId));

        if(orderSearch.getOrderStatus() != null) {
            builder.and(o.status.eq(orderSearch.getOrderStatus()));
        }

        if(hasText(orderSearch.getItemName())) {
            builder.and(i.name.contains(orderSearch.getItemName()));
        }

        List<OrderedItemDto> result = jpaQueryFactory.
                                    select( new QOrderedItemDto( i.id, i.name, i.imageSrc, o.orderDate, o.id,
                                            oi.quantity, oi.orderPrice, o.status,

                                            select(r.id)
                                                    .from(r)
                                                    .where(r.member.id.eq(o.member.id)
                                                    .and(r.item.id.eq(i.id)) ) ) ).distinct()

                                    .from(o, oi)
                                    .orderBy(o.orderDate.desc())
                                    .join(o.member, m)
                                    .join(oi.order, o)
                                    .join(oi.item, i)
                                    .where(builder)
                                    .fetch();

        return result;
    }

    //주문 상품 리스트에서 상품 하나를 주문취소하기 버튼을 누르면
    //해당 상품의 주문 아이디를 파라미터로 받아 그 상품과 같이 주문된 상품들 갖고옴
    public List<OrderedItemDto> findAllOrderItemById(long orderId) {

        QMember member = QMember.member;
        QItem item = QItem.item;
        QOrder order = QOrder.order;
        QOrderItem orderItem = QOrderItem.orderItem;

        List<OrderedItemDto> result = jpaQueryFactory.
                select(new QOrderedItemDto(item.id, item.name, item.imageSrc, order.orderDate, order.id,
                        orderItem.quantity, orderItem.orderPrice, order.status)).distinct()

                .from(order, orderItem)
                .orderBy(order.orderDate.desc())
                .join(order.member, member)
                .join(orderItem.order, order)
                .join(orderItem.item, item)
                .where(order.id.eq(orderId))
                .fetch();

        return result;
    }
}