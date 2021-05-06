import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackson.UserDTO;

/**
 * @author swamy on 12/21/20
 */

public class TestJackson {
  public static void main(String[] args) throws JsonProcessingException {
      ObjectMapper mapper = new ObjectMapper();
      UserDTO user = new UserDTO("Swamy", "Biru");
     // mapper.readValue(user);
      String dtoAsString = mapper.writeValueAsString(user);


      System.out.println(dtoAsString);
  }
}
