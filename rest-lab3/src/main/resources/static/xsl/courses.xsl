<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" />

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Курсы</title>
            </head>
            <body>
                <h1>Список курсов</h1>

                <p>
                    <a href="/api/students">Перейти к студентам (XML)</a>
                    |
                    <a href="/api/courses">Обновить список курсов (XML)</a>
                </p>

                <table border="1" cellpadding="6">
                    <tr>
                        <th>ID</th>
                        <th>Название</th>
                        <th>Описание</th>
                        <th>Студент</th>
                        <th>Ссылка на курс</th>
                    </tr>

                    <xsl:for-each select="courses/course">
                        <tr>
                            <td><xsl:value-of select="id"/></td>
                            <td><xsl:value-of select="title"/></td>
                            <td><xsl:value-of select="description"/></td>
                            <td>
                                <xsl:choose>
                                    <xsl:when test="student/id">
                                        <a>
                                            <xsl:attribute name="href">/api/students/<xsl:value-of select="student/id"/></xsl:attribute>
                                            <xsl:value-of select="student/fullName"/>
                                        </a>
                                    </xsl:when>
                                    <xsl:otherwise>
                                        (нет студента)
                                    </xsl:otherwise>
                                </xsl:choose>
                            </td>
                            <td>
                                <a>
                                    <xsl:attribute name="href">/api/courses/<xsl:value-of select="id"/></xsl:attribute>
                                    Открыть
                                </a>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
