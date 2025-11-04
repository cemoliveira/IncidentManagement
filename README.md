# Gestão de Ocorrências (IncidentManagement)

![Build Status](https://img.shields.io/badge/build-passing-brightgreen)
![Java Version](https://img.shields.io/badge/java-17+-blue)
![License](https://img.shields.io/badge/license-MIT-green)


## Descrição do Projeto

O **IncidentManagement** é um software voltado ao registro, organização, consulta e análise de ocorrências escolares em unidades de ensino. O sistema busca preencher lacunas de soluções existentes, como centralização excessiva e baixa usabilidade, oferecendo ferramentas para acompanhar situações relacionadas à disciplina, comportamento e interações no ambiente educacional.

A solução otimiza processos administrativos e fortalece o acompanhamento pedagógico, promovendo maior rastreabilidade de dados e eficiência nos fluxos escolares.

A metodologia adotada foi qualitativa e aplicada, com foco na resolução de um problema real identificado por meio de entrevistas com profissionais do Instituto SENAI. A partir dessa coleta de dados, foram definidos os requisitos funcionais, modelado o banco de dados, elaborados protótipos de telas e desenvolvido um MVP (Produto Mínimo Viável).


## Estrutura do Projeto
```
IncidentManagement/
│
├── .gitattributes
├── .gitignore
├── docker-compose.yml
├── Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
│
├── src/
│   └── main/
│       ├── java/
│       │   └── br/univesp/incidentmanagement/
│       │       ├── IncidentManagementApplication.java
│       │       │
│       │       ├── application/
│       │       │   ├── incident/
│       │       │   │   ├── dto/
│       │       │   │   │   ├── IncidentChangeStatusDTO.java
│       │       │   │   │   ├── IncidentCreateDTO.java
│       │       │   │   │   ├── IncidentDetailsDTO.java
│       │       │   │   │   ├── IncidentIniListDTO.java
│       │       │   │   │   ├── IncidentListDTO.java
│       │       │   │   │   └── IncidentUpdateDTO.java
│       │       │   │   ├── mapper/
│       │       │   │   │   └── IncidentMapper.java
│       │       │   │   └── service/
│       │       │   │       └── IncidentService.java
│       │       │   │
│       │       │   ├── schoolclass/
│       │       │   │   ├── dto/
│       │       │   │   │   ├── SchoolClassCreateDTO.java
│       │       │   │   │   ├── SchoolClassDetailsDTO.java
│       │       │   │   │   ├── SchoolClassListDTO.java
│       │       │   │   │   └── SchoolClassUpdateDTO.java
│       │       │   │   ├── mapper/
│       │       │   │   │   └── SchoolClassMapper.java
│       │       │   │   └── service/
│       │       │   │       └── SchoolClassService.java
│       │       │   │
│       │       │   ├── student/
│       │       │   │   ├── dto/
│       │       │   │   │   ├── StudentCreateDTO.java
│       │       │   │   │   ├── StudentDetailsDTO.java
│       │       │   │   │   ├── StudentListDTO.java
│       │       │   │   │   └── StudentUpdateDTO.java
│       │       │   │   ├── mapper/
│       │       │   │   │   └── StudentMapper.java
│       │       │   │   └── service/
│       │       │   │       └── StudentService.java
│       │       │   │
│       │       │   └── user/
│       │       │       ├── dto/
│       │       │       │   ├── AuthenticationDTO.java
│       │       │       │   ├── UserChangePasswordDTO.java
│       │       │       │   ├── UserCreateDTO.java
│       │       │       │   ├── UserDetailsDTO.java
│       │       │       │   ├── UserListDTO.java
│       │       │       │   ├── UserUpdateDTO.java
│       │       │       │   └── UserUpdateRoleDTO.java
│       │       │       ├── mapper/
│       │       │       │   └── UserMapper.java
│       │       │       └── service/
│       │       │           ├── AuthenticationService.java
│       │       │           └── UserService.java
│       │       │
│       │       ├── domain/
│       │       │   ├── incident/
│       │       │   │   ├── enums/
│       │       │   │   │   ├── Category.java
│       │       │   │   │   ├── Status.java
│       │       │   │   │   └── Type.java
│       │       │   │   ├── model/
│       │       │   │   │   └── Incident.java
│       │       │   │   └── repository/
│       │       │   │       └── IncidentRepository.java
│       │       │   │
│       │       │   ├── schoolclass/
│       │       │   │   ├── enums/
│       │       │   │   │   ├── Semester.java
│       │       │   │   │   └── Shift.java
│       │       │   │   ├── model/
│       │       │   │   │   └── SchoolClass.java
│       │       │   │   └── repository/
│       │       │   │       └── SchoolClassRepository.java
│       │       │   │
│       │       │   ├── student/
│       │       │   │   ├── model/
│       │       │   │   │   └── Student.java
│       │       │   │   └── repository/
│       │       │   │       └── StudentRepository.java
│       │       │   │
│       │       │   └── user/
│       │       │       ├── enums/
│       │       │       │   └── Role.java
│       │       │       ├── model/
│       │       │       │   └── User.java
│       │       │       └── repository/
│       │       │           └── UserRepository.java
│       │       │
│       │       ├── infra/
│       │       │   ├── controller/
│       │       │   │   ├── incident/IncidentController.java
│       │       │   │   ├── schoolclass/SchoolClassController.java
│       │       │   │   ├── student/StudentController.java
│       │       │   │   ├── system/HealthCheckController.java
│       │       │   │   ├── system/AuthenticationController.java
│       │       │   │   └── user/UserController.java
│       │       │   │
│       │       │   ├── documentation/AutoDocConfigurations.java
│       │       │   │
│       │       │   ├── exception/
│       │       │   │   ├── incident/
│       │       │   │   │   ├── IncidentAlreadyDeletedException.java
│       │       │   │   │   └── IncidentNotFoundException.java
│       │       │   │   ├── schoolclass/
│       │       │   │   │   ├── SchoolClassAlreadyCanceledException.java
│       │       │   │   │   └── SchoolClassNotFoundException.java
│       │       │   │   ├── student/
│       │       │   │   │   ├── StudentAlreadyDeletedException.java
│       │       │   │   │   └── StudentNotFoundException.java
│       │       │   │   └── user/
│       │       │   │       ├── InvalidPasswordException.java
│       │       │   │       ├── UserAlreadyDeletedException.java
│       │       │   │       └── UserNotFoundException.java
│       │       │   │
│       │       │   ├── persistence/
│       │       │   │   ├── incident/
│       │       │   │   │   ├── IncidentRepositoryImpl.java
│       │       │   │   │   └── JpaIncidentRepository.java
│       │       │   │   ├── schoolclass/
│       │       │   │   │   ├── JpaSchoolClassRepository.java
│       │       │   │   │   └── SchoolClassRepositoryImpl.java
│       │       │   │   ├── student/
│       │       │   │   │   ├── JpaStudentRepository.java
│       │       │   │   │   └── StudentRepositoryImpl.java
│       │       │   │   └── user/
│       │       │   │       ├── JpaUserRepository.java
│       │       │   │       └── UserRepositoryImpl.java
│       │       │   │
│       │       │   └── security/
│       │       │       ├── config/SecurityConfigurations.java
│       │       │       ├── dto/TokenJWTDTO.java
│       │       │       ├── exception/TokenJWTException.java
│       │       │       ├── filter/SecurityFilter.java
│       │       │       └── service/TokenService.java
│       │       │
│       │       └── shared/
│       │           ├── exception/
│       │           │   ├── GlobalExceptionHandler.java
│       │           │   ├── ImageStorageException.java
│       │           │   └── InvalidImageException.java
│       │           └── service/imageService.java
│       │
│       └── resources/
│           ├── db.migration.V1__create-table-alunos.sql
│           ├── db.migration.V2__alter-table-alunos-add-column-ativo.sql
│           ├── db.migration.V3__create-table-turmas.sql
│           ├── db.migration.V4__alter-table-turmas-add-column-cancelada.sql
│           ├── db.migration.V5__create-table-ocorrencias.sql
│           ├── db.migration.V6__alter-table-ocorrencias-add-column-excluida.sql
│           ├── db.migration.V7__create-table-usuarios.sql
│           ├── db.migration.V8__alter-table-usuarios-add-column-ativo.sql
│           ├── static/
│           │   └── assets/student
│           └── application.properties
```


## Enumerações Principais

- **Category**: ADMINISTRATIVA, ASSISTENCIAL, COMUNICAÇÃO, DISCIPLINAR, PEDAGÓGICA, OUTROS
- **Status**: AGUARDANDO_ATENDIMENTO, EM_ATENDIMENTO, SOLUCIONADA, ENCERRADA_SEM_SOLUÇÃO
- **Type**: Tipos detalhados para cada categoria (ex.: ATUALIZAÇÃO_DE_DADOS, AFASTAMENTO, ADVERTÊNCIA, ENTREGA_DE_ATIVIDADES, FURTO, etc.)
- **Semester**: PRIMEIRO, SEGUNDO
- **Shift**: INTEGRAL, MANHÃ, TARDE, NOITE, SÁBADO
- **Role**: ADMINISTRATIVO, ANALISTA, COORDENADOR, PROFESSOR


## Endpoints Principais

### Ocorrências (`/incidents`)
- `POST /incidents` - Criar nova ocorrência
- `GET /incidents` - Listar todas ocorrências (paginação)
- `GET /incidents/status/{status}` - Listar ocorrências por status
- `GET /incidents/categories` - Listar categorias
- `GET /incidents/types` - Listar tipos
- `PUT /incidents` - Atualizar ocorrência
- `PATCH /incidents/status` - Alterar status
- `DELETE /incidents/{id}` - Excluir ocorrência

### Turmas (`/schoolclasses`)
- `POST /schoolclasses` - Criar turma
- `GET /schoolclasses` - Listar turmas (paginação)
- `GET /schoolclasses/{id}` - Detalhes de turma
- `GET /schoolclasses/semesters` - Listar semestres
- `GET /schoolclasses/shifts` - Listar turnos
- `PUT /schoolclasses` - Atualizar turma
- `DELETE /schoolclasses/{id}` - Cancelar turma

### Alunos (`/students`)
- `POST /students` - Criar aluno
- `PATCH /students/{id}/image` - Upload de imagem
- `GET /students` - Listar alunos
- `GET /students/{id}` - Detalhes de aluno
- `PUT /students` - Atualizar aluno
- `DELETE /students/{id}` - Excluir aluno

### Usuários (`/users`)
- `POST /users` - Criar usuário
- `GET /users` - Listar usuários
- `GET /users/{id}` - Detalhes do usuário
- `GET /users/roles` - Listar papéis
- `PATCH /users` - Alterar papel
- `PATCH /users/password` - Alterar senha
- `PUT /users` - Atualizar usuário
- `DELETE /users/{id}` - Excluir usuário

### Autenticação (`/login`)
- `POST /login` - Efetuar login

### Sistema (`/health-check`)
- `GET /health-check` - Verificar status da aplicação


## Pré-requisitos

- Java 17 ou superior
- Maven 3.8+
- Docker 28+
- Docker Compose
- MySQL 8+


## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JPA/Hibernate**
- **MySQL**
- **Docker & Docker Compose**
- **Maven**


## Como Rodar o Projeto

1. Clonar o repositório:
```bash
git clone <URL_DO_REPOSITORIO>
cd IncidentManagement
```
2. Configurar application.properties com dados do banco de dados.

3. Rodar com Docker Compose:

```bash
docker-compose up --build
```
4. Aplicação disponível em http://localhost:8090


### Exemplo de criação de ocorrência

`POST /incidents`

```json
{
  "studentId": 1,
  "category": "DISCIPLINAR",
  "type": "ADVERTÊNCIA",
  "description": "Aluno chegou atrasado."
}
```


## Funcionalidades Futuras
- Dashboard de estatísticas
- Relatórios exportáveis (PDF/Excel)
- Notificações por e-mail
- Integração com sistemas escolares


## Observações

- **A aplicação segue o padrão MVP, podendo ser expandida para novas funcionalidades.**
- **Todas as APIs possuem controle de acesso baseado em roles.**
- **Enumerações de tipos e status permitem padronizar ocorrências escolares para análise e relatórios.**


## Como Contribuir
1. Faça um fork do repositório.
2. Crie uma branch com sua feature (`git checkout -b feature/nova-funcionalidade`).
3. Commit suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Push para a branch (`git push origin feature/nova-funcionalidade`).
5. Abra um Pull Request.


## Licença
Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).


## Contato
Carlos Eduardo Machado de Oliveira – cemoliveir@gmail.com