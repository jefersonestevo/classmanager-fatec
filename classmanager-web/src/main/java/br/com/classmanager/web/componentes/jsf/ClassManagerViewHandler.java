package br.com.classmanager.web.componentes.jsf;

import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.mobile.renderkit.MobileRenderKit;

public class ClassManagerViewHandler extends ViewHandlerWrapper {

	private ViewHandler wrapped;

	public ClassManagerViewHandler(ViewHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ViewHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public String calculateRenderKitId(FacesContext context) {
		// String userAgent = context.getExternalContext().getRequestHeaderMap()
		// .get("User-Agent");
		HttpServletRequest request = (HttpServletRequest) context
				.getExternalContext().getRequest();

		if (isCaminhoMobile(request)) {
			return MobileRenderKit.RENDER_KIT_ID;
		} else {
			return this.wrapped.calculateRenderKitId(context);
		}
	}

	public boolean isCaminhoMobile(HttpServletRequest request) {
		String path = request.getRequestURI().substring(
				request.getContextPath().length());
		return path.startsWith("/pages/mobile");
	}
}
