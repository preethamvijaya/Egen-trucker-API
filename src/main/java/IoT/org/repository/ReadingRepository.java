
package IoT.org.repository;

import IoT.org.entity.Reading;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ReadingRepository extends CrudRepository<Reading,String> {



}
