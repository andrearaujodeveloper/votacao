package com.associacao.votacao.service;

import com.associacao.votacao.dto.UsuarioDTO;
import com.associacao.votacao.dto.UsuarioResponse;
import com.associacao.votacao.dto.UsuarioTrocaSenhaDTO;
import com.associacao.votacao.exception.DomainBusinessException;
import com.associacao.votacao.exception.NotFoundException;
import com.associacao.votacao.mapper.UsuarioMapper;
import com.associacao.votacao.repository.UsuarioRepository;
import com.associacao.votacao.util.EmailService;
import com.associacao.votacao.util.Mensagens;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService implements IUsuarioService{

    private UsuarioRepository usuarioRepository;

    private UsuarioMapper mapper;

    private final PasswordEncoder encoder;

    private EmailService email;

    public UsuarioResponse cadastrar(UsuarioDTO usuarioDTO) {
        verificaUsuarioCadastrado(usuarioDTO.login());
        var senha = gerarSenhaAleatoria();
        var senhaEncript = encoder.encode(senha);
        var user = mapper.toEntity(usuarioDTO.login(), senhaEncript);
        email.enviarEmailParaNovoUsuario(user.getLogin(), senha);
        return mapper.toResponse(usuarioRepository.save(user));
    }

    @Override
    public void trocarSenha(UsuarioTrocaSenhaDTO usuarioTrocaSenhaDTO) {
        verificarUsuarioParaTrocaDeSenha(usuarioTrocaSenhaDTO.login(), usuarioTrocaSenhaDTO.id());
        verificarSenhaEConfirmacaoiguais(usuarioTrocaSenhaDTO.novaSenha(), usuarioTrocaSenhaDTO.confirmacao());
        var usuario = usuarioRepository.findById(usuarioTrocaSenhaDTO.id()).get();
        usuario.setSenha(usuarioTrocaSenhaDTO.novaSenha());
        usuarioRepository.save(usuario);

    }
    @Override
    public UsuarioResponse buscarPorId(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(()-> new NotFoundException(Mensagens.NAO_ENCONTRADO));
        return mapper.toResponse(usuario);
    }

    @Override
    public void apagarPorId(Long id) {
        if(!usuarioRepository.existsById(id)){
            throw  new NotFoundException(Mensagens.NAO_ENCONTRADO);
        }
        usuarioRepository.deleteById(id);
    }

    private void verificaUsuarioCadastrado(String login) {
        if(usuarioRepository.existsByLogin(login)){
            throw new DomainBusinessException("Usuario já Cadastrado");
        }
    }

    private String gerarSenhaAleatoria() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    private void verificarUsuarioParaTrocaDeSenha(String login, Long id) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Mensagens.NAO_ENCONTRADO));

        if(!usuarioRepository.existsByLogin(login) || !usuario.getUsername().equals(login)){
          throw new DomainBusinessException(Mensagens.ERRO_AO_ALTERAR_SENHA);
        }
    }

    private void verificarSenhaEConfirmacaoiguais(String senha, String verificacao) {
        if(!senha.equals(verificacao)){
            throw new DomainBusinessException(Mensagens.ERRO_AO_ALTERAR_SENHA);
        }
    }

}
