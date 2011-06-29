package com.app.android;

public class User 
{
	private String email = "";
	private String vorName = "";
	private String name = "";
	private String andere = "";
	
	public  void setVorname (String vorName)        
	{            
		this.vorName = vorName;      
	}
	
	public String getVorname ()        
	{            
		return vorName;        
	}
	
	public  void setName (String name)        
	{            
		this.name = name;      
	}
	
	public String getName ()        
	{            
		return name;        
	}
	
	public  void setEmail (String email)        
	{            
		this.email = email;      
	}
	
	public String getEmail ()        
	{            
		return email;        
	}
	
	public  void setAndere (String andere)        
	{            
		this.andere = andere;      
	}
	
	public String getAndere ()        
	{            
		return andere;        
	}

}
