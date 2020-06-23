package IoT.org.service;



import IoT.org.exception.VehicleNotFoundException;
import IoT.org.repository.VehicleRepository;
import IoT.org.entity.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImp implements VehicleService{

    @Autowired
    private VehicleRepository repo;

    @Transactional
    public List<Vehicle> findAll(){return (List<Vehicle>) repo.findAll();}


    @Transactional
    public Vehicle findOne(String id) {
        Optional<Vehicle> v = repo.findById(id);
        if(!v.isPresent())      {
            throw new VehicleNotFoundException("Vehicle VIN= "+id+" is not found in database");
        }
        return v.get();

    }

    @Transactional
    public List<Vehicle> update(List<Vehicle> v) {

        for (int i = 0; i <v.size() ; i++) {
            repo.save(v.get(i));
        }
        return v;
//        return (List<Vehicle>) repo.save(v);
    }



}


