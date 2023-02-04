## Spring Boot Security | MongoDB | JWT Authentication
Dans ce repo, nous allons créer un exemple d'authentification Spring Boot (Connexion et inscription) et d'autorisation basée sur les rôles avec JWT, Spring Security et Spring Data MongoDB.

### Technologie
---
- Java 11
- Spring Boot 2.7.3 (with Spring Security, Spring Web, Spring Data MongoDB)
- jjwt 0.9.1
- Lombok
- MapStruct
- MongoDB
- Maven 3+
- Tout IDE prenant en charge Java et Spring Boot (Spring Tool Suite (STS), IntelliJ, VSC, NetBeans, etc.)
- Postman, curl ou n'importe quel client HTTP pour tester l'API REST

### Explication codes sources
---
- `WebSecurityConfigurerAdapter` - est le cœur de notre implémentation de sécurité. Il fournit des HttpSecurityconfigurations pour configurer cors, csrf, la gestion de session, les règles pour les ressources protégées.
- `UserDetailsService` - interface a une méthode pour charger l'utilisateur par nom d' utilisateur et renvoie un objet `UserDetails` que Spring Security peut utiliser pour l'authentification et la validation.
- `UserDetails` - contient les informations nécessaires (telles que : nom d'utilisateur, mot de passe, autorités) pour construire un objet d'authentification.
- `UsernamePasswordAuthenticationToken` obtient {nom d'utilisateur, mot de passe} à partir de la demande de connexion, `AuthenticationManager` l'utilisera pour authentifier un compte de connexion
- `AuthenticationManager` a un `DaoAuthenticationProvider` (avec l'aide de `UserDetailsService` & `PasswordEncoder`) pour valider l'objet `UsernamePasswordAuthenticationToken`. En cas de succès, `AuthenticationManager` renvoie un objet d'authentification entièrement rempli (y compris les autorités accordées).
- `OncePerRequestFilter` fait une seule exécution pour chaque requête à notre API. Il fournit une méthode `doFilterInternal()` que nous allons implémenter pour analyser et valider JWT, charger les détails de l'utilisateur (à l'aide de `UserDetailsService`), vérifier l'autorisation (à l'aide de `UsernamePasswordAuthenticationToken`).
- `AuthenticationEntryPoint` détectera l'erreur d'authentification.
- `WebSecurityConfig` s'étend `WebSecurityConfigurerAdapter`
- `UserDetailsServiceImpl` implemente `UserDetailsService`
- `UserDetailsImpl` implemente `UserDetails`
- `AuthEntryPointJwt` implemente `AuthenticationEntryPoint`
- `AuthTokenFilter` s'étend `OncePerRequestFilter`
- `JwtUtils` fournit des méthodes pour générer, analyser, valider JWT

### Cas d'utilisation
---
- L'utilisateur peut créer un nouveau compte ou se connecter avec un nom d'utilisateur et un mot de passe.
- Par le rôle de l'utilisateur (administrateur, utilisateur), nous autorisons l'utilisateur à accéder aux ressources (autorisation basée sur les rôles)

### Exécuter et tester l'application
---
- Exécuter l'application `mvn spring-boot:run`
- Importer dans Postman la collection `mongodb_security.postman_collection.json` pour tester les APIs
- Vous pouver utiliser aussi l'url du Swagger ou importer l'url Swagger dans Postman
    - Les descriptions OpenAPI seront disponibles au chemin `/v3/api-docs` par défaut : http://localhost:8081/v3/api-docs/ et/ou http://localhost:8081/swagger-ui/index.html