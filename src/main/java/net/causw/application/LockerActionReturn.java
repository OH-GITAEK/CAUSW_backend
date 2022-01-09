package net.causw.application;

import lombok.NoArgsConstructor;
import net.causw.application.spi.LockerPort;
import net.causw.domain.exceptions.BadRequestException;
import net.causw.domain.exceptions.ErrorCode;
import net.causw.domain.model.LockerDomainModel;
import net.causw.domain.model.Role;
import net.causw.domain.model.UserDomainModel;
import net.causw.domain.validation.ConstraintValidator;
import net.causw.domain.validation.ContentsAdminValidator;
import net.causw.domain.validation.ValidatorBucket;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class LockerActionReturn implements LockerAction{
    @Override
    public Optional<LockerDomainModel> updateLockerDomainModel(
            LockerDomainModel lockerDomainModel,
            UserDomainModel ownerDomainModel,
            UserDomainModel updaterDomainModel,
            Validator validator,
            LockerPort lockerPort

    ) {
        if (ownerDomainModel == null) {
            throw new BadRequestException(
                    ErrorCode.CANNOT_PERFORMED,
                    "사용 중인 사물함이 아닙니다."
            );
        }

        ValidatorBucket.of()
                .consistOf(ContentsAdminValidator.of(
                        updaterDomainModel.getRole(),
                        updaterDomainModel.getId(),
                        ownerDomainModel.getId(),
                        List.of(Role.PRESIDENT)
                ))
                .validate();

        lockerDomainModel.returnLocker();

        return lockerPort.update(
                lockerDomainModel.getId(),
                lockerDomainModel
        );
    }
}
