package com.associacao.votacao.util;

import com.associacao.votacao.exception.MailException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {

    @Mock
    private JavaMailSender mail;

    @InjectMocks
    private EmailService service;

    @Test
    void deveEnviarDeCadastro() {
        var destinatario = "andre@email.com";
        var senha = "12345";
        var mensagem = mock(MimeMessage.class);
        when(mail.createMimeMessage()).thenReturn(mensagem);

        service.enviarEmailParaNovoUsuario(destinatario, senha);

        verify(mail).send(mensagem);
    }

    @Test
    void deveLancarExceptionAoTentarEnviarEmail() {
        var destinatario = "andre@email.com";
        var senha = "12345";
        var mensagem = mock(MimeMessage.class);
        when(mail.createMimeMessage()).thenReturn(mensagem);
        doThrow(MailException.class).when(mail).send(mensagem);

        assertThrows(MailException.class, () -> service.enviarEmailParaNovoUsuario(destinatario, senha));
    }
}