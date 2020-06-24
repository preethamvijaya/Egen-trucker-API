package IoT.org.service;



import IoT.org.exception.VehicleNotFoundException;
import IoT.org.repository.VehicleRepository;
import IoT.org.entity.VehicleInfo;
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
    public List<VehicleInfo> findAll(){return (List<VehicleInfo>) repo.findAll();}


    @Transactional
    public VehicleInfo findOne(String id) {
        Optional<VehicleInfo> v = repo.findById(id);
        if(!v.isPresent())      {
            throw new VehicleNotFoundException("VehicleInfo VIN= "+id+" is not found in database");
        }
        return v.get();

    }

    @Transactional
    public List<VehicleInfo> update(List<VehicleInfo> v) {

        for (int i = 0; i <v.size() ; i++) {
            repo.save(v.get(i));
        }
        return v;
//        return (List<VehicleInfo>) repo.save(v);
    }



}


