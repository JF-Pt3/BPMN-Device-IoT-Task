<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml" encoding="ISO-8859-1" indent="yes"/>

<xsl:template match="/result">

<xmi:XMI xmlns:xmi="http://www.omg.org/XMI" xmlns:org.mda="http://org/mda" xmi:version="2.0">

<org.mda:Package name="org.mda">

<task name="Task_1">
  <xsl:for-each select="xmi2xmi">
  
	

	<xsl:if test="@task='Task_1'">	
	
	
   
    <xsl:variable name="valor" select="*"/>
      <xsl:variable name="attributeName" select="@*"/> 
	  
	  
					
								<xsl:element name="fields">
									
								 <xsl:attribute name="name"> 
								   <xsl:value-of select="name(@*)" /> 
								  </xsl:attribute>	
								
								
								  <xsl:attribute name="value"> 
								   <xsl:value-of select="$attributeName" /> 
								  </xsl:attribute>
								</xsl:element>
	
		</xsl:if>
  </xsl:for-each>
 </task>
 
 
 </org.mda:Package>
</xmi:XMI>
</xsl:template>
</xsl:stylesheet>
