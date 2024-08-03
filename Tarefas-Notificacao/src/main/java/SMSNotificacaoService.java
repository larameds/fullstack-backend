
public class SMSNotificacaoService implements NotificacaoService {
	@Override
	public void enviaNotificacao(String message) {
		System.out.println("Enviando notificação por SMS: " + message);
	}
}
