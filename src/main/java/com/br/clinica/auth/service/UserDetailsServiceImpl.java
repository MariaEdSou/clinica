package com.br.clinica.auth.service;

import com.br.clinica.auth.controller.*;
import com.br.clinica.auth.dto.DadosAtualizacaoUserDTO;
import com.br.clinica.auth.dto.UserDTO;
import com.br.clinica.auth.dto.UserResponseDTO;
import com.br.clinica.auth.model.UserModel;
import com.br.clinica.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.function.Predicate.not;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepositoryy;
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositoryy.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }

    @Transactional
    public void save(UserDTO userDTO) {
        userRepositoryy.save(new UserModel(userDTO));
        log.info("registered user");
    }

    public Optional<List<UserResponseDTO>> list(Pageable paginacao) {
        List<UserResponseDTO> userResponseDTOS = userRepositoryy.findAll(paginacao)
                .map(UserResponseDTO::new)
                .toList();

        return Optional.of(userResponseDTOS)
                .filter(not(List::isEmpty));
    }

    @Transactional
    public void update(String id, DadosAtualizacaoUserDTO dadosAtualizacao) {
        UserModel user = userRepositoryy.getReferenceById(id);

        user.update(dadosAtualizacao);
        userRepositoryy.save(user);
        log.info("update data");
    }
}

