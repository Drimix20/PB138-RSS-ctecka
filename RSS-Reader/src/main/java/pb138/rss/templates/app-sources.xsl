
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns="http://www.w3.org/1999/xhtml">
   
   
  <!-- authors:
  Marek Turis
  Richard Žember
  -->  
    <xsl:output method="html"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                encoding="UTF-8"
                indent="yes"
    />    

    <xsl:template match="/sources">
        <style>
            body {
            background-color: #FFECCC;
            font-family: Arial
            }           
            img {
            float: top;
            }
            hr {
            clear: both;
            }
        </style>
        <div class="content">
            <xsl:apply-templates select="//item">
                <xsl:with-param name="cat" select="."/>
            </xsl:apply-templates>
        </div>
    </xsl:template>
    
    <xsl:template match="item">
        <div class="item">
           
          <h1>
            <strong>
                <a href="{./link}" style="color: #7D2713">
                    <xsl:value-of select="./title"/>
                </a>
            </strong>
            <br/>
           </h1>
            
            <span class='date'>
                <strong>
                <xsl:value-of select="./date"/>
                <separator> </separator>                   
                    <font color="737373">
                        <xsl:value-of select="../title"/> 
                    </font>                        
                </strong>
            </span>           
            <p>            
                <xsl:value-of select="./description" disable-output-escaping="yes"/>                
                <hr/>
            </p>
        </div>        
    </xsl:template>
</xsl:stylesheet>
