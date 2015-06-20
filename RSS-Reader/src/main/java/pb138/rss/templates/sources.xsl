<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : sources.xsl
    Author     : Marek
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
    <xsl:output method="html"/>
    
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
        <html>
            <head>
                <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
                <title>Feedy</title>
                <style>
                    body {
                    font-family: Arial
                    }
                    
                    .container {
                    width: 900px;
                    margin: 0px auto;
                    position: relative;
                    }
                    
                    .categories {
                    width: 200px;
                    position: absolute;
                    overflow: auto;
                    }
                    
                    .categories h3 {
                    color: green;
                    }
                    
                    .categories a {
                    color: black;
                    text-decoration: none;
                    }
                    
                    .content {
                    width: 700px;
                    position: absolute;
                    left: 200px;
                    }
                    
                    .categories ul {
                    list-style-type: none;
                    padding-left: 0px;
                    font-size: 20px;
                    }
                    
                    .item h3 {
                    clear: both;
                    margin-bottom: 5px;
                    }
                    
                    .item h3 a {
                    color: green;
                    }
                    
                    .item .date {
                    color: grey;
                    font-size: 12px;
                    }
                    
                    .item img {
                    float: left;
                    margin-right: 10px;
                    margin-bottom: 10px;
                    }
                    
                    a.active {
                    color: black;
                    }
                    
                    .arrows {
                    display: none;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="categories">
                        <h3>Kategórie</h3>
                        <ul>
                            <xsl:for-each select="//source[not(@category=preceding-sibling::source/@category)]/@category">
                                <li>
                                    <a class='category-href' data-category="{.}" href='javascript:;'>
                                        <xsl:choose>
                                            <xsl:when test=". = 'none'">
                                                Bez kategórie
                                            </xsl:when>
                                            <xsl:otherwise>
                                                <xsl:value-of select="."/>     
                                            </xsl:otherwise>
                                        </xsl:choose> 
                                        <span class="arrows">&#160;&gt;&gt;</span>
                                    </a>
                                </li>
                            </xsl:for-each>
                        </ul>
                    </div>
                    <div class="content">
                        <xsl:for-each select="//source[not(@category=preceding-sibling::source/@category)]/@category">
                            <div id="{.}" class="content-category">
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
                                    <xsl:with-param name="cat" select="."/>
                                </xsl:apply-templates>
                            </div>
                        </xsl:for-each>
                        
                        <!--<xsl:for-each select="//source[not(@category=preceding-sibling::source/@category)]/@category">
                            <li><a class='category-href' href='javascript:;'><xsl:value-of select="."/></a></li>
                        </xsl:for-each>-->
                    </div>
                </div>
            </body>
        </html>
        
        <script>
            $(document).ready(function() {
            $(".category-href").click(function() {
            //$(".category-href").removeClass("active");
            //$(this).addClass("active");
            $(".arrows").hide();
            $(".arrows", $(this)).show();
            var category = $(this).attr("data-category");
            $(".content-category").hide();
            $("#" + category).show();
            })
            
            $(".category-href").first().click();
            });
        </script>
    </xsl:template>
    
    <xsl:template match="item">
        <xsl:param name="cat"/>
        <xsl:if test="./../@category = $cat">
            <div class="item">
                <h3>
                    <a href="{./link}">
                        <xsl:value-of select="./title"/>
                    </a>
                </h3>
                <span class="date">
                    <xsl:value-of select="./pubDate"/>
                </span>
                <p>
                    <xsl:value-of select="./description" disable-output-escaping="yes" />
                </p>
            </div>
        </xsl:if>
    </xsl:template>

</xsl:stylesheet>
