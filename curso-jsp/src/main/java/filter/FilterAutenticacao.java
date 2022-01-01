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


@WebFilter(urlPatterns = {"/principal/*"}) //Interceta todas as requisições que vier do projeto ou mapeamento
public class FilterAutenticacao implements Filter {


    public FilterAutenticacao() {
    }

	/*Encerra os processos quando o servidor é parado
	 * Ex. Mataria os processos de conexão com o banco
	 * Validação de autenticação, dar commit e rollback de transações no banco,
	 * validar e fazer redirecionamento de páginas*/
	public void destroy() {
	}

	/*Intercepta todas as requisições e dar as respostas no sistema
	 * Ex. Toda requisição do sistema vai passar por aqui*/
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("user");
		
		String urlParaAutenticar = req.getServletPath(); //url que está sendo acessada
		
		/*Validar se está logado, se não redireciona para a tela de login*/
		if(usuarioLogado == null && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServeletLogin") ) { //Não está logado
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redireciona.forward(request, response);
			return; //Para a execução e redireciona para o login
			
		}else {
			
			chain.doFilter(request, response);
		}
		
	}

	/*Inicia os processos ou recursos quando o servidor sobe o projeto
	 * Ex. Iniciar a conexão com o banco de dados 
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
