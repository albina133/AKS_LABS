<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="html" encoding="UTF-8" />

    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Курс</title>
            </head>
            <body>
                <h1>Данные курса</h1>

                <p>
                    <a href="/api/courses">← Назад к списку курсов (XML)</a>
                    |
                    <a href="/api/students">Перейти к студентам (XML)</a>
                </p>

                <table border="1" cellpadding="6">
                    <tr><th>ID</th><td><xsl:value-of select="Course/id | course/id"/></td></tr>
                    <tr><th>Название</th><td><xsl:value-of select="Course/title | course/title"/></td></tr>
                    <tr><th>Описание</th><td><xsl:value-of select="Course/description | course/description"/></td></tr>
                </table>

                <h2>Студент</h2>

                <xsl:choose>
                    <xsl:when test="(Course/student/id | course/student/id)">
                        <p>
                            <b>
                                <xsl:value-of select="Course/student/fullName | course/student/fullName"/>
                            </b>
                            (ID:
                            <xsl:value-of select="Course/student/id | course/student/id"/>
                            )
                        </p>
                        <p>
                            <a>
                                <xsl:attribute name="href">/api/students/<xsl:value-of select="Course/student/id | course/student/id"/></xsl:attribute>
                                Открыть студента
                            </a>
                        </p>
                    </xsl:when>
                    <xsl:otherwise>
                        <p>(курс не привязан к студенту)</p>
                    </xsl:otherwise>
                </xsl:choose>

            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
