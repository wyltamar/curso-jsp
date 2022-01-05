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


@WebFilter(urlPatterns = {"/principal/*"}) //Intercepta todas as requisições que vier do projeto ou mapeamento
public class FilterAutenticacao implements Filter {


    public FilterAutenticacao() {
    }

	
	public void destroy() {
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("user");
		
		String urlParaAutenticar = req.getServletPath(); //url que está sendo acessada
		
		/*Validar se está logado, se não redireciona para a tela de login*/
		if((usuarioLogado == null || usuarioLogado.equals("null"))  && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServeletLogin") ) { //Não está logado
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login");
			redireciona.forward(request, response);
			return; //Parar a execução e redirecionar para o login
			
		}else {
			
			chain.doFilter(request, response);
		}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
