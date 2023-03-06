package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.controller.dto.PaymentResponseDTO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;

@Mapper(componentModel = "spring")
public interface PaymentDTOMapper {

    PaymentResponseDTO toPaymentResponseDTO(PaymentResponseDO paymentResponseDO);
}
