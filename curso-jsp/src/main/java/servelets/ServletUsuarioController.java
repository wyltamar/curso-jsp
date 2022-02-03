package servelets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOUsuarioRepository;
import model.ModelLogin;


@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String msg = "Registro salvo com sucesso!";
    
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
   
    public ServletUsuarioController() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String id = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin modelLogin = new ModelLogin();
		modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id): null);
		modelLogin.setNome(nome);
		modelLogin.setEmail(email);
		modelLogin.setLogin(login);
		modelLogin.setSenha(senha);
		
			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
			msg = "J� existe usu�rio com este login, informe um outro login!";
		}else {
			
			if(modelLogin.getId() != null) {
				modelLogin = daoUsuarioRepository.atualizarUsuario(modelLogin);
				msg = "Registro atualizado com sucesso!";
			}else {
			modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin);
			msg = "Registro salvo com sucesso!";
			}
		}
		
		request.setAttribute("msg", msg);
		
		request.setAttribute("modelLogin", modelLogin);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirect = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirect.forward(request, response);
		}
		
	}

}
