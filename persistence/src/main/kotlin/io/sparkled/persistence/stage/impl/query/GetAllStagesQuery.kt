package io.sparkled.persistence.stage.impl.query

import io.sparkled.model.entity.Stage
import io.sparkled.persistence.PersistenceQuery
import io.sparkled.persistence.PersistenceQuery.Companion.qStage
import io.sparkled.persistence.QueryFactory

class GetAllStagesQuery : PersistenceQuery<List<Stage>> {

    override fun perform(queryFactory: QueryFactory): List<Stage> {
        return queryFactory
                .selectFrom(qStage)
                .orderBy(qStage.name.asc())
                .fetch()
    }
}