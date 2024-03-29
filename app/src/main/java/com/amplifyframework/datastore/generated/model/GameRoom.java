package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.HasMany;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the GameRoom type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "GameRooms")
public final class GameRoom implements Model {
  public static final QueryField ID = field("id");
  public static final QueryField SEED = field("seed");
  public static final QueryField GAMERS = field("gamers");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String seed;
  private final @ModelField(targetType="Int") Integer gamers;
  private final @ModelField(targetType="Player") @HasMany(associatedWith = "gameroom", type = Player.class) List<Player> players = null;
  public String getId() {
      return id;
  }
  
  public String getSeed() {
      return seed;
  }
  
  public Integer getGamers() {
      return gamers;
  }
  
  public List<Player> getPlayers() {
      return players;
  }
  
  private GameRoom(String id, String seed, Integer gamers) {
    this.id = id;
    this.seed = seed;
    this.gamers = gamers;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      GameRoom gameRoom = (GameRoom) obj;
      return ObjectsCompat.equals(getId(), gameRoom.getId()) &&
              ObjectsCompat.equals(getSeed(), gameRoom.getSeed()) &&
              ObjectsCompat.equals(getGamers(), gameRoom.getGamers());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getSeed())
      .append(getGamers())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("GameRoom {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("seed=" + String.valueOf(getSeed()) + ", ")
      .append("gamers=" + String.valueOf(getGamers()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static GameRoom justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new GameRoom(
      id,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      seed,
      gamers);
  }
  public interface BuildStep {
    GameRoom build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep seed(String seed);
    BuildStep gamers(Integer gamers);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String seed;
    private Integer gamers;
    @Override
     public GameRoom build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new GameRoom(
          id,
          seed,
          gamers);
    }
    
    @Override
     public BuildStep seed(String seed) {
        this.seed = seed;
        return this;
    }
    
    @Override
     public BuildStep gamers(Integer gamers) {
        this.gamers = gamers;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String seed, Integer gamers) {
      super.id(id);
      super.seed(seed)
        .gamers(gamers);
    }
    
    @Override
     public CopyOfBuilder seed(String seed) {
      return (CopyOfBuilder) super.seed(seed);
    }
    
    @Override
     public CopyOfBuilder gamers(Integer gamers) {
      return (CopyOfBuilder) super.gamers(gamers);
    }
  }
  
}
