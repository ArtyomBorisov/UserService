package it.academy.service.impl;

import it.academy.constants.UserRole;
import it.academy.repositories.RoleRepository;
import it.academy.dto.UserRequestDto;
import it.academy.dto.UserResponseDto;
import it.academy.entity.RoleEntity;
import it.academy.entity.UserEntity;
import it.academy.exception.EmailExistsException;
import it.academy.exception.ServerException;
import it.academy.repositories.UserRepository;
import it.academy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConversionService conversionService;
    private final int pageSize;
    private final int sort;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           ConversionService conversionService,
                           @Value("${settings.page_size}") int pageSize,
                           @Value("${settings.sort}") int sort) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.conversionService = conversionService;
        this.pageSize = pageSize;
        this.sort = sort;
    }

    @Override
    @Transactional
    public void add(UserRequestDto userRequestDto) {
        logger.debug("new user adding");
        String email = userRequestDto.getEmail();

        if(userRepository.existsByEmail(email)) {
            logger.debug("user exception: email exists");
            throw new EmailExistsException();
        }

        UserRole role = userRequestDto.getRole();
        RoleEntity roleEntity = roleRepository.findByRole(role);

        UserEntity entityForSaving = conversionService.convert(userRequestDto, UserEntity.class);
        if(entityForSaving == null) {
            logger.error("after conversion (dto to entity) null has been received");
            throw new ServerException(ServerException.Causes.CONVERSION);
        }

        entityForSaving.setRoleEntity(roleEntity);
        userRepository.save(entityForSaving);
        logger.debug("new user has been saved");
    }

    @Override
    public List<UserResponseDto> getAll() {
        logger.debug("all users list getting");
        return findAllByOrder()
                .stream()
                .map(entity -> conversionService.convert(entity, UserResponseDto.class))
                .toList();
    }

    private List<UserEntity> findAllByOrder() {
        return switch (sort) {
            case 1 -> userRepository.findAllByOrderByEmailAsc();
            case 2 -> userRepository.findAllByOrderByLastNameAsc();
            default -> throw new ServerException(ServerException.Causes.NOT_KNOWN_SORT);
        };
    }

    @Override
    public Page<UserResponseDto> getPage(int page) {
        logger.debug("users page getting");
        return findPageByOrder(page)
                .map(entity -> conversionService.convert(entity, UserResponseDto.class));
    }

    private Page<UserEntity> findPageByOrder(int page) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(page);

        return switch (sort) {
            case 1 -> userRepository.findAllByOrderByEmailAsc(pageable);
            case 2 -> userRepository.findAllByOrderByLastNameAsc(pageable);
            default -> throw new ServerException(ServerException.Causes.NOT_KNOWN_SORT);
        };
    }
}
