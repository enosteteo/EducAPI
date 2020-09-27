# Educ API

Springboot API that provides contexts and challenges in a collaborative database.

![GitHub](https://img.shields.io/github/license/a4s-ufpb/EducAPI?label=licence) ![Type Badge](https://img.shields.io/badge/project%3A-Apps4Society-informational) ![enter image description here](https://img.shields.io/badge/project-SisAlfa-yellow)  ![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/a4s-ufpb/EducAPI?color=blueviolet)

## 🛠 Tech Stack

To make this project run you will need to have the following items installed: 

* Docker and Docker Compose
* Java 11 or OpenJDK 11
* PostgreSQL 12.3 (it is recommended to use a Docker container)
* Intelij or other IDE of your choice

This project also uses the following technologies:
* Spring Boot - 2.2.7.RELEASE
* JPA
* Hibernate
* H2 Database
* Maven 4.0.0 
* Swagger 2.9.2
* JUnit 4

 > Note: Use Insomnia or another tool to test calls to API endpoints

## :rocket: Getting Started

To start this project, follow the instructions according to the desired mode. You must first clone this repository:

    # To clone the repository
    git clone https://github.com/a4s-ufpb/EducAPI.git

 > Note: Pull requests should only be accepted if the project is able to build using Docker. 
 
 ### Starting the project
 
Change the `spring.profiles.active` variable in the `application.properties` file to `dev` or `test`
 
* Development environment: 

Open the IDE of your choice and start the project from the class`src/main/java/br/ufpb/dcx/apps4society/educapi/EducAPIAplication.java`.

Go to `http://localhost:8080/`to access the API.

* Development environment with Docker: 

Open in the default project directory:

    cd EducAPI

To start this project type in the terminal, inside the project's default directory:


    docker-compose up -d
 
 
To stop this project type in the terminal, inside the project's default directory:

    docker-compose down
    
Whenever you want to test any changes that werw made to the code, repeat the commands above to stop and start again.
 
Go to `http://localhost:8080/`to access the API.


## :closed_book:Documentation

<!-- As EducAPI has already been documented in Swagger, you don't need to document the  endpoints here, but if it did not, it should be documented in this section with subtopics (take https://github.com/EmersonDantas/SWR-Social-Network-API as base) -->

You can obtain documentation for this project by going to [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html) after cloning the project,  or accessing the documentation available online at [`https://educapi-a4sufpb.herokuapp.com/swagger-ui.html`](https://educapi-a4sufpb.herokuapp.com/swagger-ui.html).

To learn more about [Spring boot](https://spring.io/guides#getting-started-guides), how to install and how to use it, access the documentation. 
Also access the [JUnit](https://junit.org/junit4/), [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference) and [Hibernate](https://hibernate.org/orm/documentation/5.4/) documentation.


## :page_facing_up: Licence

This project is licensed under the MIT License, see also [LICENSE.md](https://github.com/a4s-ufpb/LICENSE.md) for more details.

## 👩‍💻👨‍💻 Contributors 

<table>
  <tr>
    <td align="center">
	    <a href="https://github.com/AmandaAzevedo">
		    <img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/44630609?s=460&u=ff38a29379fef41570bb4850ae4ed4f456873d0d&v=4" width="100px;" alt=""/>
		    <br/><sub><b>Amanda Azevedo</b></sub>
		</a></br>
    </td>
    <td align="center">
	    <a href="https://github.com/EmersonDantas">
		    <img style="border-radius: 50%;" src="https://avatars2.githubusercontent.com/u/30247063?s=460&u=51304437a75b46e7048abd5e1cef2128d59c78f5&v=4" width="100px;" alt=""/>
		    <br/><sub><b>Emerson Dantas</b></sub>
		</a></br>
    </td>
    <td align="center">
	    <a href="https://github.com/NapoleaoHerculano">
		    <img style="border-radius: 50%;" src="https://avatars3.githubusercontent.com/u/33008128?s=460&u=ad3187526aff8c3976abf00160c8ddcb5feec685&v=4" width="100px;" alt=""/>
		    <br/><sub><b>Francivaldo Napoleão</b></sub>
		</a></br>
    </td>
    <td align="center">
	    <a href="https://github.com/MarcosLudgerio">
		    <img style="border-radius: 50%;" src="https://avatars0.githubusercontent.com/u/43012976?s=460&u=1163c04d9f35b577063b3f6550ae520c4dd2f866&v=4" width="100px;" alt=""/>
		    <br/><sub><b>Marcos Ludgero</b></sub>
		</a></br>
    </td>
  </tr>
</table>

### Project coordinator
<table>
	<tr>
		<td align="center">
		    <a href="https://github.com/ayladebora">
			    <img style="border-radius: 50%;" src="https://avatars1.githubusercontent.com/u/1224119?s=460&v=4" width="100px;" alt=""/>
		    <br/><sub><b>Ayla Dantas Rebouças</b></sub>
		</a>
		</br>
    </td>
    <td align="center">
	    <a href="https://github.com/cesdias">
		    <img style="border-radius: 50%;" src="https://avatars1.githubusercontent.com/u/18514013?s=460&u=0c92f6ba0e7145b56d4c2a56b92b63eea07855ca&v=4" width="100px;" alt=""/>
		    <br/><sub><b>Carlos Hacks</b></sub>
		</a></br>
    </td>
	</tr>
</table>

### :handshake: How to contribute? 
Please read [CONTRIBUTING.md](https://github.com/a4s-ufpb/EducAPI/blob/master/CONTRIBUTING.md) for more details regarding our content code and the process of submitting pull requests to us.

## :mailbox: Contact us
* To learn more about the Apps4Society and SisAlfa project visit the websites [![Site Apps4Society](https://img.shields.io/twitter/url?color=blue&label=Site%20Apps4Society&logo=Apps4Society&style=plastic&url=https%3A%2F%2Fapps4society.dcx.ufpb.br)](https://apps4society.dcx.ufpb.br) [![Site Sisalfa](https://img.shields.io/twitter/url?color=yellow&label=Site%20SisAlfa&logo=Sisalfa&style=plastic&url=https%3A%2F%2Fsisalfa.dcx.ufpb.br%2F) ](https://sisalfa.dcx.ufpb.br)
* Follow the Apps4Society on Instagram  [![Twitter URL](https://img.shields.io/twitter/url?color=pink&label=Instagram%20Apps4Society&logo=Instagram&logoColor=rose&style=plastic&url=https%3A%2F%2Fwww.instagram.com%2Fapps4society%2F)](https://instagram.com/apps4society) 
* Contact us by email [![Gmail Badge](https://img.shields.io/badge/-apps4society@dcx.ufpb.br-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:apps4society@dcx.ufpb.br)](apps4society@dcx.ufpb.br) [![Gmail Badge](https://img.shields.io/badge/-sisalfa@dcx.ufpb.br-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:sisalfa@dcx.ufpb.br)](sisalfa@dcx.ufpb.br)
---

