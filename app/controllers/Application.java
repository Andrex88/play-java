package controllers;

import models.Task;
import models.prueba;
import models.users;
import play.data.Form;
import play.mvc.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class Application extends Controller {
    public static Form<Task> taskForm = Form.form(Task.class);
    public static Form<users> userForm = Form.form(users.class);

    public Result index() {
        prueba test1 = new prueba("Cristian","Palacio",27);
        JsonNode nodo = Json.toJson(test1);
        //response().setContentType("text/html");
        //return ok("<h1>Hello World!</h1>");
        response().setContentType("application/json");
        return ok(Json.stringify(nodo));
    }
    
    public Result indexXML(){
        prueba test1 = new prueba("Cristian","Palacio",27);
        StringWriter writer = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(prueba.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(test1, writer);
            response().setContentType("application/xml");
            String theXML = writer.toString();
            return ok(theXML);
        }catch(Exception e){
            response().setContentType("text/html");
            return ok("<p>tenemos un problema houston!</p>"+e.toString());
        }
    }

     public Result get() throws ExecutionException, InterruptedException {
         conectando variable = new conectando();
         response().setContentType("application/json");
         return ok(variable.get());
     }

     public Result post() {
         JsonNode json = request().body().asJson();
         response().setContentType("text/html");
         if(json == null) {
             return badRequest("Expecting Json data");
         }else{
             conectando variable = new conectando();
             return ok(variable.post(json));
         }
     }

     public Result delete() {
         JsonNode json = request().body().asJson();
         response().setContentType("text/html");
         if(json == null) {
             return badRequest("Expecting Json data");
         }else{
             conectando variable = new conectando();
             return ok(variable.post(json));
         }
     }

     public Result put() {
         JsonNode json = request().body().asJson();
         response().setContentType("text/html");
         if(json == null) {
             return badRequest("Expecting Json data");
         }else{
             conectando variable = new conectando();
             return ok(variable.post(json));
         }
     }

    public Result inicio(){
        return ok(
                views.html.index.render(users.all(), userForm)
        );
    }

     /*public Result inicio(){
         response().setContentType("text/html");
         return ok("Dale pues");
     }

     public static Result guardar() {
         Form<User> form = form(User.class).bindFromRequest();
         if (form.hasErrors()) {
             return badRequest("/registration");
         } else {
             User user = form.get();
             //JPA.em().persist(user);
             System.out.println("User "+user.getName()+" Completamentamente guardado!");
             return redirect("/index.scala.html");
         }
     }

     public Result inicio(){
         return ok(views.html.index.render(Task.all(), taskForm));
     }
*/
    public Result tasks() {
        return TODO;
    }

    public Result seleccionarUsuario(int id) {
        users usuario = users.seleccionar(id);
        Form<users> filledForm = userForm.fill(usuario);
        return  ok(views.html.index.render(users.all(), filledForm));
    }

    public Result nuevoUsuario() {
        Form<users> filledForm = userForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest(
                    (views.html.index.render(users.all(), userForm))
            );
        } else {
            users.crear(filledForm.get());
            return redirect(routes.Application.inicio());
        }
    }

    public Result deleteUsuario(int id) {
        Form<users> filledForm = userForm.bindFromRequest();
        if(filledForm.hasErrors()) {
            return badRequest((views.html.index.render(users.all(), userForm)));
        } else {
            users.borrar(id);
            return redirect(routes.Application.inicio());
        }
    }
}