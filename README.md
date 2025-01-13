# 1. Introducerea Proiectului
## Scopul aplicației 
  Personal Library Management este de a permite utilizatorilor să gestioneze eficient o bibliotecă personală. Aplicația oferă funcționalități pentru administrarea cărților, categoriilor, recenziilor și utilizatorilor, toate integrate într-un mediu sigur și ușor de utilizat.
## Contextul Aplicației 
  Aplicația a fost dezvoltată folosind Spring Boot pentru backend, MySQL pentru gestionarea bazei de date și Swagger pentru documentarea API-ului.

# 2. Cerințele Aplicației
## Cerințe Funcționale
 ### - Autentificare și Autorizare:
  > Înregistrarea și autentificarea utilizatorilor.
  > Validarea acreditărilor și generarea unui token
 ### - Gestionarea Cărților:
  > Adăugarea, actualizarea progresului de lectură și afișarea tuturor cărților.
 ### - Gestionarea Categoriilor:
  > Adăugarea și ștergerea categoriilor de cărți.
  > Afișarea tuturor categoriilor.
 ### - Gestionarea Recenziilor:
  > Adăugarea și afișarea recenziilor pentru o carte specifică.

## Cerințe Non-Funcționale
  ### - Securitate:
  > Implementarea securității cu Spring Security și BCrypt pentru criptarea parolelor.

  ### - Performanță:
  > Optimizarea interogărilor din baza de date.

  ### - Compatibilitate:
  > Compatibilitate cu Postman pentru testare API.

# 3. Arhitectura și Design
## Structura Aplicației
Aplicația urmează modelul MVC (Model-View-Controller):

**Model**: Entitățile aplicației precum User, Book, Category, și Review.
**View**: Swagger UI pentru explorarea API-urilor.
**Controller**: Clasele responsabile pentru expunerea endpoint-urilor API.
**Service**: Logica de business pentru fiecare funcționalitate.
**Repository**: Interfațe pentru comunicarea cu baza de date.

## Tehnologii Utilizate

**Spring Boot**: Framework principal pentru backend.
**MySQL**: Sistem de gestionare a bazelor de date relaționale.
**Swagger**: Documentarea API-urilor.
**JUnit și Mockito**: Testare unitară și de integrare.
**Lombok**.



**10 cerințe de business:**-
- Gestionarea utilizatorilor.
- Autentificare și autorizare.
- Adăugarea și gestionarea cărților.
- Adăugarea și gestionarea categoriilor.
- Adăugarea recenziilor pentru cărți.
- Securitate cu Spring Security.
- Criptare parole cu BCrypt.
- Compatibilitate cu Postman.
- Performanță optimizată pentru baze de date.
- Documentarea completă cu Swagger.
