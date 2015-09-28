package controllers;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.users;

import java.util.List;

/**
 * Created by cristian.palacio on 25/09/2015.
 */
public class conectando {

    Session session;
    Cluster cluster;

    public conectando() {
        cluster = Cluster.builder().addContactPoint("localhost").build();
        session = cluster.connect("mykeyspace");
    }

    public String get() {
        MappingManager manager = new MappingManager (session);
        UserAccessor userAccessor = manager.createAccessor(UserAccessor.class);
        Result<users> users = userAccessor.getAll();
        List<models.users> temp = users.all();
        return new Gson().toJson(temp);
    }

    public String post(JsonNode Juser) {
        MappingManager manager = new MappingManager(session);
        Mapper<users> mapper = manager.mapper(users.class);
        users user = new Gson().fromJson(Juser.toString(),users.class);
        try {
            mapper.save(user);
            return user.getFname()+"guardado";
        }catch (Exception e){
            return user.getFname()+"no guardado";
        }
    }
   }

