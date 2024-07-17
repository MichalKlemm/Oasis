package repository;

import java.util.List;
import dataUser.DataUser;

public interface UserRepository {

    int save(DataUser datauser);

    int update(DataUser datauser);

    DataUser findById(int id);

    DataUser findByPersonId(String personid);

    int deleteById(int id);

    List<DataUser> findAll();
}
