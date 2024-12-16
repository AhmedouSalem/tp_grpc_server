package org.isd.tpgrpcservicehotelreservation.utils;

import java.util.Optional;

import io.grpc.*;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.isd.tpgrpcservicehotelreservation.entities.Login;
import org.isd.tpgrpcservicehotelreservation.repository.LoginRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@GrpcGlobalServerInterceptor
public class AuthorizationUtil implements ServerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizationUtil.class);

	private final LoginRepository loginRepository;

	// Définition de la clé de contexte pour stocker l'objet Login
	public static final Context.Key<Login> LOGIN_CONTEXT_KEY = Context.key("login");

	private static final Metadata.Key<String> IDENTIFIANT_KEY =
			Metadata.Key.of("identifiant", Metadata.ASCII_STRING_MARSHALLER);
	private static final Metadata.Key<String> PASSWORD_KEY =
			Metadata.Key.of("password", Metadata.ASCII_STRING_MARSHALLER);

	public AuthorizationUtil(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	public Optional<Login> validateAuthorization(String identifiant, String password) {
		if (identifiant != null && password != null) {
			return loginRepository.findByIdentifiantAndPassword(identifiant, password);
		}

		return Optional.empty();
	}

	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
																 Metadata headers,
																 ServerCallHandler<ReqT, RespT> next) {
		String identifiant = headers.get(IDENTIFIANT_KEY);
		String password = headers.get(PASSWORD_KEY);

		Optional<Login> loginOpt = validateAuthorization(identifiant, password);

		if (loginOpt.isEmpty()) {
			call.close(Status.UNAUTHENTICATED.withDescription("Invalid credentials" + identifiant + password), headers);
			return new ServerCall.Listener<>() {};
		}

		// Stocker le login dans le contexte
		Context context = Context.current().withValue(LOGIN_CONTEXT_KEY, loginOpt.get());
		return Contexts.interceptCall(context, call, headers, next);
	}
}
