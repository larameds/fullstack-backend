import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/tasks")
public class TaskServlet extends HttpServlet{
	private TarefaService tarefaService; 
	
	public void init() throws ServletException{
		NotificacaoService notificacaoService = new EmailNotificacaoService();
		tarefaService = new TarefaService(notificacaoService);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		String task = request.getParameter("task");
		if(task != null && !task.isEmpty()) {
			tarefaService.adicionar(task);
			response.setStatus(HttpServletResponse.SC_CREATED);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tarefa inválida");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    response.setContentType("text/plain");
	    PrintWriter out = response.getWriter();

	    List<String> tarefas = tarefaService.listar();
	    if (tarefas.isEmpty()) {
	        out.println("Não há tarefas disponíveis.");
	    } else {
	        for (int i = 0; i < tarefas.size(); i++) {
	            out.println((i + 1) + ". " + tarefas.get(i));
	        }
	    }
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        int index = Integer.parseInt(request.getParameter("index")) - 1;
	        String task = request.getParameter("task");
	        if (index >= 0 && task != null && !task.isEmpty()) {
	            tarefaService.atualizar(index, task);
	        } else {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetros inválidos.");
	        }
	    } catch (NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Índice inválido.");
	    } catch (IndexOutOfBoundsException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tarefa não encontrada.");
	    }
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        int index = Integer.parseInt(request.getParameter("index")) - 1;
	        if (index >= 0) {
	            tarefaService.remover(index);
	        } else {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Índice inválido.");
	        }
	    } catch (NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Índice inválido.");
	    } catch (IndexOutOfBoundsException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tarefa não encontrada.");
	    }
	}

}
