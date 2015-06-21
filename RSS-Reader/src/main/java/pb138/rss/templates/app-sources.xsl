
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
    
    <xsl:variable name="vrtfMonths">
        <m name="Jan" num="01"/>
        <m name="Feb" num="02"/>
        <m name="Mar" num="03"/>
        <m name="Apr" num="04"/>
        <m name="May" num="05"/>
        <m name="Jun" num="06"/>
        <m name="Jul" num="07"/>
        <m name="Aug" num="08"/>
        <m name="Sep" num="09"/>
        <m name="Oct" num="10"/>
        <m name="Nov" num="11"/>
        <m name="Dec" num="12"/>
    </xsl:variable>

    <xsl:variable name="vMonths" select=
   "document('')/*/xsl:variable
                   [@name='vrtfMonths']/*"
    />   

    <xsl:template match="/sources">
        <style>
            body {
            background-color: #FFECCC;
            font-family: Arial
            }           
            img {
            float: left;
            }
            hr {
            clear: both;
            }
        </style>
        <div class="content">
            <xsl:apply-templates select="//item">
                <xsl:sort data-type="number" order="descending" select=
        "concat(substring(pubDate,13,4),
                $vMonths[@name 
                        = 
                         substring(current()/pubDate,9,3)]/@num,

                substring(pubDate,6,2),
                translate(substring(pubDate,18,8),
                          ':',
                          ''
                          )
                )
         "/>
                
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
                    <xsl:value-of select="./pubDate"/>
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
