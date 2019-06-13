<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
		xpath-default-namespace="http://example.com/my">
	<xsl:output method="xml" encoding="utf-8" indent="yes" />

	<xsl:template match="bookshelf">
		<subsidiary>
			<informations>
				<xsl:apply-templates select="information"/>
			</informations>
			<statistic>
				<xsl:call-template name="statistic"/>
			</statistic>
			<books>
				<xsl:apply-templates select="books"/>
			</books>
			<book_authors_list>
				<xsl:apply-templates select="book_authors_list"/>
			</book_authors_list> 
		</subsidiary>
	</xsl:template>

	<xsl:template match="information">
		<name>Bookshelf</name>
		<generated>
			<xsl:value-of select="modification_date"/> 
		</generated> 
		<description>
			<xsl:value-of select="description"/>
		</description>
		<xsl:for-each select="authors/author">
			<author>
				<xsl:value-of select="name"/>
				<xsl:text> </xsl:text>
				<xsl:value-of select="surname"/>
				<xsl:text>, Indeks: </xsl:text>
				<xsl:value-of select="indeks"/>
			</author>		
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="statistic">
		<position_count>
			<xsl:value-of select="$position_count"/>
		</position_count>
		<avg_bookshelf_value>
			<xsl:value-of select="concat((format-number($avg_value,'#.00')),' PLN')"/>
		</avg_bookshelf_value>
		<number_polish_items>
			<xsl:value-of select="count(//book/language[@id_language='pl'])"/>
		</number_polish_items>
		<number_eng_items>
			<xsl:value-of select="count(//book/language[@id_language='eng'])"/>
		</number_eng_items>	
		<number_book_for_adult>
			<xsl:value-of select="count(//book/age_category[@id_age_category='18'])"/> 
		</number_book_for_adult>

		<xsl:for-each select="//book">
			<xsl:sort select="realise_day"/>
			<xsl:if test="position()=1">
				<oldest_book>
					<title>
						<xsl:value-of select="title"/>
					</title>
					<realise_day>
						<xsl:value-of select="realise_day"/>
					</realise_day>
				</oldest_book>
			</xsl:if>
			<xsl:if test="position()=last()">
				<youngest_book>
					<title>
						<xsl:value-of select="title"/>
					</title>
					<realise_day>
						<xsl:value-of select="realise_day"/>
					</realise_day>
				</youngest_book>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="books">
		<xsl:apply-templates select ="book">
			<xsl:sort select="title" order="ascending"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="book">
		<book>
			<ISBN>
				<xsl:value-of select="@ISBN"/>
			</ISBN>
			<title>
				<xsl:value-of select="title"/>
			</title>
				<language>
					<xsl:choose>
						<xsl:when test="language/@id_language='eng'">
							<xsl:text>english</xsl:text>
						</xsl:when>
						<xsl:when test="language/@id_language='pl'">
							<xsl:text>polish</xsl:text>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>other</xsl:text>
						</xsl:otherwise>
					</xsl:choose> 
				</language>	
				<xsl:for-each select="key('myAuthorID',book_author/@author_id)/.">
					<author>
						<name>
							<xsl:value-of select="name" />
						</name>
						<surname>
							<xsl:value-of select="surname" />
						</surname>
						<author_nation>
							<xsl:choose>
								<xsl:when test="author_nation/@id_author_nation='us'">
									<xsl:text>USA</xsl:text>
								</xsl:when>
								<xsl:when test="author_nation/@id_author_nation='pl'">
									<xsl:text>Poland</xsl:text>
								</xsl:when>
								<xsl:otherwise>
									<xsl:text>other</xsl:text>
								</xsl:otherwise>
							</xsl:choose> 
						</author_nation>
					</author>		
				</xsl:for-each>
			<binding>
				<xsl:value-of select="binding/@id_binding"/>
			</binding>
			<dimensions>
				<xsl:value-of select="dimensions/width"/>
				<xsl:value-of select="' x '"/>
				<xsl:value-of select="dimensions/height"/>
				<xsl:value-of select="dimensions/@id_dimension"/>
			</dimensions>
			<price>
				<xsl:value-of select="price"/>
				<xsl:value-of select="' '"/>
				<xsl:value-of select="price/@id_currency"/>
			</price>
			<if_for_adult>
				<xsl:choose>
					<xsl:when test="age_category/@id_age_category='18'">
						<xsl:text>yes</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>no</xsl:text>
					</xsl:otherwise>
				</xsl:choose> 
			</if_for_adult>
		</book>
	</xsl:template>

	<xsl:template match="book_authors_list">
		<xsl:apply-templates select="book_author_info">
			<xsl:sort select="surname" order="descending"/>
		</xsl:apply-templates>
	</xsl:template>

	<xsl:template match="book_author_info">
		<book_author>
			<name>
				<xsl:value-of select="./name"/>
			</name>
			<surname>
				<xsl:value-of select="./surname"/>
			</surname>
			<author_nation>
				<xsl:choose>
					<xsl:when test="author_nation/@id_author_nation='us'">
						<xsl:text>USA</xsl:text>
					</xsl:when>
					<xsl:when test="author_nation/@id_author_nation='pl'">
						<xsl:text>Poland</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>other</xsl:text>
					</xsl:otherwise>
				</xsl:choose> 
			</author_nation>
		</book_author>
	</xsl:template>

	<xsl:variable name="position_count" select="count(//book)"/>
	<xsl:variable name="euro_exchange_rate" select="4.28"/>
	<xsl:variable name="value_euro_for_zl" select="sum(//book/price[@id_currency='EUR']) * $euro_exchange_rate" />
	<xsl:variable name="value_in_zl" select="sum(//book/price[@id_currency='PLN'])"/>
	<xsl:variable name="total_value" select="$value_in_zl + $value_euro_for_zl"/>
	<xsl:variable name="avg_value" select= "$total_value div $position_count"/>   

	<xsl:key name="myAuthorID" match="book_author_info"  use="@author_id"/> 
</xsl:stylesheet>