import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

public class TarefaService {
	private final NotificacaoService notificacaoService;
	private List<String> tasks = new ArrayList<>();
	
	public TarefaService(NotificacaoService notificacaoService) {
		this.notificacaoService = notificacaoService;
	}
	
	public void adicionar(String task) {
		tasks.add(task);
		notificacaoService.enviaNotificacao("Nova tarefa adiocionada: " + task);
	}
	
	public List<String> listar() {
	    return tasks;
	}

	public void atualizar(int index, String novaTarefa) {
	    if (index >= 0 && index < tasks.size()) {
	        tasks.set(index, novaTarefa);
	        notificacaoService.enviaNotificacao("Tarefa atualizada: " + novaTarefa);
	    } else {
	        throw new IndexOutOfBoundsException("Tarefa não encontrada.");
	    }
	}

	public void remover(int index) {
	    if (index >= 0 && index < tasks.size()) {
	        String tarefaRemovida = tasks.remove(index);
	        notificacaoService.enviaNotificacao("Tarefa removida: " + tarefaRemovida);
	    } else {
	        throw new IndexOutOfBoundsException("Tarefa não encontrada.");
	    }
	}
}
