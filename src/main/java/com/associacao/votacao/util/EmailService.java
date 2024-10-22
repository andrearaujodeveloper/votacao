package com.associacao.votacao.util;


import com.associacao.votacao.exception.MailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public void enviarEmailParaNovoUsuario(String destinatario, String senhaAleatória) {

        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem);

            helper.setTo(destinatario);
            helper.setSubject(Mensagens.SUBJECT_EMAIL_CADASTRO_USUARIO);
            helper.setText(Mensagens.AVISO_EMAIL_CADASTRO_USUARIO +
                    "\nusuario: " + destinatario +
                    "\nsenha: " + senhaAleatória +
                    Mensagens.RECOMENDACAO_TROCA_SENHA);
            mailSender.send(helper.getMimeMessage());

        } catch (MessagingException e) {
            throw new MailException("Não foi possível enviar de confirmação de cadastro");
        }
    }

}
