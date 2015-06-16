<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : app-sources.xsl
    Created on : Utorok, 2015, júna 16, 19:12
    Author     : Marek
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/sources">
        <div class="content">
            <xsl:apply-templates select="//item">
                <xsl:with-param name="cat" select="."/>
            </xsl:apply-templates>
        </div>
    </xsl:template>
    
    <xsl:template match="item">
        <div class="item">
            <h3>
                <xsl:value-of select="./title"/>
            </h3>
            <a href="{./link}"><xsl:value-of select="./link"/></a><br/>
            <span class="date">
                <xsl:value-of select="./date"/>
            </span>
            <p>
                <xsl:value-of select="./description"  /><!-- disable-output-escaping="yes"-->
            </p>
        </div>
        <hr/>
    </xsl:template>

</xsl:stylesheet>
