package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"}) //Interceta todas as requisi��es que vier do projeto ou mapeamento
public class FilterAutenticacao implements Filter {


    public FilterAutenticacao() {
    }

	/*Encerra os processos quando o servidor � parado
	 * Ex. Mataria os processos de conex�o com o banco
	 * Valida��o de autentica��o, dar commit e rollback de transa��es no banco,
	 * validar e fazer redirecionamento de p�ginas*/
	public void destroy() {
	}

	/*Intercepta todas as requisi��es e dar as respostas no sistema
	 * Ex. Toda requisi��o do sistema vai passar por aqui*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("user");
		
		String urlParaAutenticar = req.getServletPath(); //url que est� sendo acessada
		
		/*Validar se est� logado, se n�o redireciona para a tela de login*/
		if(usuarioLogado == null && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServeletLogin") ) { //N�o est� logado
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redireciona.forward(request, response);
			return; //Para a execu��o e redireciona para o login
			
		}else {
			
			chain.doFilter(request, response);
		}
		
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto
	 * Ex. Iniciar a conex�o com o banco de dados 
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
