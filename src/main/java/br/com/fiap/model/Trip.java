package br.com.fiap.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@DynamoDBTable(tableName = "trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/** Trip : id, date (YYYY/MM/DD),
country, city, URL repository for photos **/
public class Trip {

	@DynamoDBHashKey(attributeName = "id")
	@DynamoDBAutoGeneratedKey
	private String id;

	@DynamoDBRangeKey(attributeName = "tripDate")
	private String tripDate;

	@DynamoDBAttribute(attributeName = "country")
	private String country;

	@DynamoDBAttribute(attributeName = "city")
	private String city;

	@DynamoDBAttribute(attributeName = "url")
	private String url;
}
