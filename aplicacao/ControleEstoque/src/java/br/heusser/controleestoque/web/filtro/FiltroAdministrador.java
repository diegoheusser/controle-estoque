package br.heusser.controleestoque.web.filtro;

import br.heusser.controleestoque.controle.LoginBean;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author diego
 */
public class FiltroAdministrador implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        LoginBean beanLogin = (LoginBean) sessao.getAttribute("beanLogin");

        if (beanLogin.getUsuario().isAdministrador()) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher encaminhador = request.getRequestDispatcher("/sistema/index.jsf");
            encaminhador.forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

}
