/*
 * Robot Wars API
 * Eine Api für das Spiel Robot Wars
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.IOException;
import java.math.BigDecimal;
/**
 * NewRobot
 */


public class NewRobot {
  @SerializedName("name")
  private String name = null;

  @SerializedName("health")
  private BigDecimal health = null;

  @SerializedName("attackDamage")
  private BigDecimal attackDamage = null;

  @SerializedName("attackRange")
  private BigDecimal attackRange = null;

  @SerializedName("movementRate")
  private BigDecimal movementRate = null;

  public NewRobot name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name des Roboters
   * @return name
  **/
  @Schema(example = "Wall e", required = true, description = "Name des Roboters")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public NewRobot health(BigDecimal health) {
    this.health = health;
    return this;
  }

   /**
   * Leben des Roboters
   * @return health
  **/
  @Schema(example = "10", required = true, description = "Leben des Roboters")
  public BigDecimal getHealth() {
    return health;
  }

  public void setHealth(BigDecimal health) {
    this.health = health;
  }

  public NewRobot attackDamage(BigDecimal attackDamage) {
    this.attackDamage = attackDamage;
    return this;
  }

   /**
   * Schaden des Roboters
   * @return attackDamage
  **/
  @Schema(example = "10", required = true, description = "Schaden des Roboters")
  public BigDecimal getAttackDamage() {
    return attackDamage;
  }

  public void setAttackDamage(BigDecimal attackDamage) {
    this.attackDamage = attackDamage;
  }

  public NewRobot attackRange(BigDecimal attackRange) {
    this.attackRange = attackRange;
    return this;
  }

   /**
   * Angriffradius des Roboters
   * @return attackRange
  **/
  @Schema(example = "10", required = true, description = "Angriffradius des Roboters")
  public BigDecimal getAttackRange() {
    return attackRange;
  }

  public void setAttackRange(BigDecimal attackRange) {
    this.attackRange = attackRange;
  }

  public NewRobot movementRate(BigDecimal movementRate) {
    this.movementRate = movementRate;
    return this;
  }

   /**
   * Bewegungsradius des Roboters
   * @return movementRate
  **/
  @Schema(example = "10", required = true, description = "Bewegungsradius des Roboters")
  public BigDecimal getMovementRate() {
    return movementRate;
  }

  public void setMovementRate(BigDecimal movementRate) {
    this.movementRate = movementRate;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NewRobot newRobot = (NewRobot) o;
    return Objects.equals(this.name, newRobot.name) &&
        Objects.equals(this.health, newRobot.health) &&
        Objects.equals(this.attackDamage, newRobot.attackDamage) &&
        Objects.equals(this.attackRange, newRobot.attackRange) &&
        Objects.equals(this.movementRate, newRobot.movementRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, health, attackDamage, attackRange, movementRate);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewRobot {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    health: ").append(toIndentedString(health)).append("\n");
    sb.append("    attackDamage: ").append(toIndentedString(attackDamage)).append("\n");
    sb.append("    attackRange: ").append(toIndentedString(attackRange)).append("\n");
    sb.append("    movementRate: ").append(toIndentedString(movementRate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
