<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <list>
            <xsl:for-each select="List/item">
                <item>
                    <id>
                        <xsl:value-of select="id"/>
                    </id>
                    <balance>
                        <id>
                            <xsl:value-of select="balance/id"/>
                        </id>
                        <value>
                            <xsl:value-of select="balance/value"/>
                        </value>
                    </balance>
                </item>
            </xsl:for-each>
        </list>
    </xsl:template>
</xsl:stylesheet>