package controllers;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Query;
import com.datastax.driver.mapping.annotations.Param;
import models.users;

import java.util.UUID;

/**
 * Created by cristian.palacio on 25/09/2015.
 */
@Accessor
public interface UserAccessor {
    @Query("SELECT * FROM mykeyspace.users")
    public Result<users> getAll();

}
