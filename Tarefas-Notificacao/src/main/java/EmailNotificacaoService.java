

public class EmailNotificacaoService implements NotificacaoService {
    @Override
    public void enviaNotificacao(String mensagem) {
        System.out.println("Enviando notificação por e-mail: " + mensagem);
    }
}