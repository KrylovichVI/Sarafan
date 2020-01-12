<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <message-form :messageAttr="message"/>
            <message-row v-for="message in sortedMessages"
                         :message="message"
                         :key="message.id"
                         :editMessage="editMessage"/>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapGetters } from 'vuex'
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        computed: mapGetters(['sortedMessages']),
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
        }
    }
</script>

<style></style>