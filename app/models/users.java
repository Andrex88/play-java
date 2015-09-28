package models;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.google.gson.Gson;
import controllers.UserAccessor;
import play.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristian.palacio on 25/09/2015.
 */
@Table(keyspace = "mykeyspace", name = "users")
public class users implements java.io.Serializable {
    @PartitionKey
    public Integer user_id;
    public String fname;
    public String lname;

    public static List<users> all(){
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect("mykeyspace");
        MappingManager manager = new MappingManager (session);
        UserAccessor userAccessor = manager.createAccessor(UserAccessor.class);
        Result<users> users = userAccessor.getAll();
        List<users> usuarios = new ArrayList<>();
        try {
            usuarios = users.all();
        }catch (Exception e){
        }
        return usuarios;
    }

    public static users seleccionar(int id){
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect("mykeyspace");
        MappingManager manager = new MappingManager (session);
        Mapper<users> mapper = manager.mapper(users.class);
        return mapper.get(id);
    }

    public static String crear(users user){
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect("mykeyspace");
        MappingManager manager = new MappingManager(session);
        Mapper<users> mapper = manager.mapper(users.class);
        try {
            mapper.save(user);
            return user.getFname()+"guardado";
        }catch (Exception e){
            return user.getFname()+"no guardado";
        }
    }

    public static String borrar(Integer id){
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect("mykeyspace");
        MappingManager manager = new MappingManager(session);
        Mapper<users> mapper = manager.mapper(users.class);
        try {
            return "borrado";
        }catch (Exception e){
            return "no borrado";
        }
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
