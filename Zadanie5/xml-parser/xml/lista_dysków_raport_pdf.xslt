<?xml version="1.0" encoding="utf-8" ?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:msxsl="urn:schemas-microsoft-com:xslt"
                exclude-result-prefixes="msxsl">
    <xsl:output method="xml"
                encoding="utf-8"/>


    <xsl:template match="/">
        <fo:root font-family="sans-serif">

            <fo:layout-master-set>
                <fo:simple-page-master master-name="Podsumowanie" page-height="297mm" page-width="210mm"
                                       margin-top="5mm" margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
                    <fo:region-body margin-top="10mm" margin-bottom="10mm"/>
                    <fo:region-after region-name="xsl-region-after" extent="10mm"/>
                </fo:simple-page-master>
            </fo:layout-master-set>

            <fo:page-sequence master-reference="Podsumowanie">

                <fo:static-content flow-name="xsl-region-after">
                    <fo:block>
                        <xsl:apply-templates select="/dokument/ciało/podsumowanie/data_wygenerowania"/>
                    </fo:block>
                </fo:static-content>

                <fo:flow flow-name="xsl-region-body">

                    <fo:block font-weight="bold" text-align="center" font-size="large">
                        <xsl:text>Liczba dysków w sklepach:</xsl:text>
                    </fo:block>
                    <xsl:apply-templates select="/dokument/ciało/lista_sklepów/sklep"/>

                    <fo:block font-weight="bold" text-align="center" font-size="large" margin-top="10mm">
                        <xsl:text>Lista dysków:</xsl:text>
                    </fo:block>
                    <fo:table margin-left="2mm" margin-right="2mm" margin-bottom="10mm" margin-top="5mm"
                              table-layout="fixed" width="196mm" font-size="8px">
                        <fo:table-column column-width="proportional-column-width(2)"/>
                        <fo:table-column column-width="proportional-column-width(2)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-column column-width="proportional-column-width(1)"/>
                        <fo:table-header>
                            <fo:table-row>
                                <fo:table-cell text-align="left">
                                    <fo:block font-weight="bold">Marka</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="left">
                                    <fo:block font-weight="bold">Model</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="left">
                                    <fo:block font-weight="bold">Pojemnosc</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="left">
                                    <fo:block font-weight="bold">Cena</fo:block>
                                </fo:table-cell>
                                <fo:table-cell text-align="left">
                                    <fo:block font-weight="bold">Ocena</fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <xsl:apply-templates select="/dokument/ciało/lista_dysków"/>
                        </fo:table-body>
                    </fo:table>

                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="/dokument/ciało/lista_sklepów/sklep">
        <fo:block border-color="black" border-width="0.6mm" border-style="solid" margin-top="5mm" margin-bottom="5mm"
                  margin-left="50mm" margin-right="50mm">
            <fo:list-block>
                <fo:list-item>
                    <fo:list-item-label>
                        <fo:block>
                            Nazwa:
                        </fo:block>
                        <fo:block font-style="italic">
                            <xsl:value-of select="nazwa"/>
                        </fo:block>
                    </fo:list-item-label>
                    <fo:list-item-body>
                        <fo:block margin-left="12mm" text-align="right">
                            Liczba dysków:
                        </fo:block>
                        <fo:block margin-left="12mm" text-align="right">
                            <xsl:value-of select="liczba_dysków"/>
                        </fo:block>
                    </fo:list-item-body>
                </fo:list-item>
            </fo:list-block>
        </fo:block>
    </xsl:template>

    <xsl:template match="/dokument/ciało/lista_dysków">
        <xsl:for-each select="dysk">
            <fo:table-row line-height="20mm" top="2mm" bottom="2mm" left="2mm" right="2mm" border-color="black"
                          border-width="0.6mm" border-style="solid">
                <fo:table-cell>
                    <fo:block line-height="5mm" font-style="italic">
                        <xsl:value-of select="marka"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm" font-style="italic">
                        <xsl:value-of select="model"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="pojemność"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="cena"/>
                    </fo:block>
                </fo:table-cell>
                <fo:table-cell>
                    <fo:block line-height="5mm">
                        <xsl:value-of select="ocena"/>
                    </fo:block>
                </fo:table-cell>
            </fo:table-row>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="/dokument/ciało/podsumowanie/data_wygenerowania">
        <fo:block text-align="right">
            <xsl:value-of select="concat('Data wygenerowania raportu: ',.)"/>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>