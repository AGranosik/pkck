<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xpath-default-namespace="http://example.com/saps">
    <xsl:output method="xml"
                encoding="utf-8"
                indent="yes"/>
    <xsl:strip-space elements="*"/>
    <xsl:template match="/">
        <dokument>
            <nagłówek>
                <autorzy>
                    <xsl:for-each select="/dokument/nagłówek/autorzy/autor">
                        <xsl:variable name="autor" select="."/>
                        <autor>
                            <imię>
                                <xsl:value-of select="$autor/imię"/>
                            </imię>
                            <nazwisko>
                                <xsl:value-of select="$autor/nazwisko"/>
                            </nazwisko>
                            <numer_indeksu>
                                <xsl:value-of select="$autor/numer_indeksu"/>
                            </numer_indeksu>
                        </autor>
                    </xsl:for-each>
                </autorzy>
                <data>
                    <xsl:value-of select="current-dateTime()"/>
                </data>
            </nagłówek>
            <xsl:apply-templates/>
        </dokument>
    </xsl:template>

    <xsl:template match="nagłówek"/>

    <xsl:template match="ciało">
        <ciało>
            <lista_sklepów>
                <xsl:for-each select="lista_sklepów/sklep">
                    <xsl:sort select="@id" data-type="text"/>
                    <xsl:variable name="id_sklepu" select="@id"/>
                    <xsl:variable name="liczba_dysków_ze_sklepu"
                                  select="count(/dokument/ciało/lista_dysków/dysk[@sklep = $id_sklepu])"/>
                    <xsl:if test="$liczba_dysków_ze_sklepu &gt; 0">
                        <sklep>
                            <nazwa>
                                <xsl:value-of select="@nazwa"/>
                            </nazwa>
                            <liczba_dysków>
                                <xsl:value-of select="$liczba_dysków_ze_sklepu"/>
                            </liczba_dysków>
                        </sklep>
                    </xsl:if>
                </xsl:for-each>
            </lista_sklepów>

            <lista_dysków>
                <xsl:for-each select="lista_dysków/dysk">
                    <xsl:sort select="@id" data-type="text"/>
                    <xsl:variable name="id_dysku" select="@id"/>
                    <xsl:variable name="dysk" select="/dokument/ciało/lista_dysków/dysk[@id = $id_dysku]"/>
                    <xsl:variable name="id_sklepu" select="$dysk/@sklep"/>
                    <xsl:variable name="sklep" select="/dokument/ciało/lista_sklepów/sklep[@id = $id_sklepu]"/>
                    <dysk>
                        <marka>
                            <xsl:value-of select="$dysk/marka"/>
                        </marka>
                        <model>
                            <xsl:value-of select="$dysk/model"/>
                        </model>
                        <pojemność>
                            <xsl:value-of select="$dysk/pojemność, $dysk/pojemność/@jednostka"/>
                        </pojemność>
                        <cena>
                            <xsl:value-of select="$dysk/cena, $dysk/cena/@waluta"/>
                        </cena>
                        <ocena>
                            <xsl:value-of select="$dysk/ocena"/>
                        </ocena>
                    </dysk>
                </xsl:for-each>
            </lista_dysków>

            <podsumowanie>
                <suma>
                    <xsl:value-of
                            select="format-number(sum(for $dysk in /dokument/ciało/lista_dysków/dysk return /dokument/ciało/lista_dysków/dysk[@id = $dysk/@id]/cena),'.##')"/>
                </suma>
                <liczba_dysków>
                    <xsl:value-of select="count(/dokument/ciało/lista_dysków/dysk)"/>
                </liczba_dysków>
                <liczba_sklepów>
                    <xsl:value-of select="count(/dokument/ciało/lista_sklepów/sklep)"/>
                </liczba_sklepów>
                <liczba_typów>
                    <xsl:value-of select="count(distinct-values(/dokument/ciało/lista_dysków/dysk/@typ))"/>
                </liczba_typów>
                <data_wygenerowania>
                    <xsl:value-of select="current-date()"/>
                </data_wygenerowania>
            </podsumowanie>
        </ciało>
    </xsl:template>
</xsl:stylesheet>
